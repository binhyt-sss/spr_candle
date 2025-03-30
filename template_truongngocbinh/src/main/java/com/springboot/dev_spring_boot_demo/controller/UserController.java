package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.User;
import com.springboot.dev_spring_boot_demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list-user";
    }

    @GetMapping("/form-insert")
    public String userFormInsert(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form-insert";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // Kiểm tra xem form là insert hay update
            boolean isUpdate = userService.existsById(user.getUsername());
            return isUpdate ? "user-form-update" : "user-form-insert";
        }

        // Kiểm tra xem là tạo mới hay cập nhật
        boolean isNewUser = !userService.existsById(user.getUsername());

        // Mã hóa mật khẩu trước khi lưu
        String rawPassword = user.getPassword();
        if (rawPassword != null && !rawPassword.startsWith("{bcrypt}")) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);
        }

        user.setEnabled(true);
        userService.save(user);

        // Nếu là user mới, thêm role mặc định
        if (isNewUser) {
            // Thêm ROLE_CUSTOMER làm role mặc định
            jdbcTemplate.update(
                "INSERT INTO authorities (username, authority) VALUES (?, ?)",
                user.getUsername(), "ROLE_CUSTOMER"
            );
        }

        return "redirect:/user/list";
    }

    @GetMapping("/form-update")
    public String formUpdate(@RequestParam("username") String username, Model model) {
        User user = userService.findById(username);
        model.addAttribute("user", user);
        return "user-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("username") String username) {
        userService.deleteById(username);
        return "redirect:/user/list";
    }

    @GetMapping("/edit-roles/{username}")
    public String showEditRolesForm(@PathVariable String username, Model model) {
        // Get current authorities for the user
        List<String> authorities = jdbcTemplate.queryForList(
                "SELECT authority FROM authorities WHERE username = ?",
                String.class,
                username
        );

        model.addAttribute("username", username);
        model.addAttribute("authorities", authorities);
        return "edit-roles";
    }

    @PostMapping("/update-roles")
    public String updateRoles(@RequestParam String username,
                              @RequestParam(required = false) List<String> roles) {
        // Delete existing roles
        jdbcTemplate.update("DELETE FROM authorities WHERE username = ?", username);

        // Add new roles if any were selected
        if (roles != null) {
            for (String role : roles) {
                // Add ROLE_ prefix if not present
                String authority = role.startsWith("ROLE_") ? role : "ROLE_" + role;
                jdbcTemplate.update(
                        "INSERT INTO authorities (username, authority) VALUES (?, ?)",
                        username, authority
                );
            }
        }

        return "redirect:/user/list";
    }

    @GetMapping("/encode-all-passwords")
    public String encodeAllPasswords() {
        List<User> users = userService.findAll();
        for (User user : users) {
            String rawPassword = user.getPassword();
            if (rawPassword != null && !rawPassword.startsWith("{bcrypt}")) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                user.setPassword(encodedPassword);
                userService.save(user);
            }
        }
        return "redirect:/user/list";
    }
}
