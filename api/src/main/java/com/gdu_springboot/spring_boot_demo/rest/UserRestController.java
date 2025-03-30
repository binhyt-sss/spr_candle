package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.User;
import com.gdu_springboot.spring_boot_demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService UserService;

    public UserRestController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return UserService.findAll();
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return UserService.findById(id);
    }
    @PostMapping("/users")
    public User addUser(@RequestBody User User) {
        UserService.save(User);
        return User;
    }
    @PutMapping("/users")
    public User updateUser(@RequestBody User User) {
        User oldUser = UserService.save(User);
        return oldUser;
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        UserService.deleteById(id);
        return "Delete success has id = "+ id;
    }
}
