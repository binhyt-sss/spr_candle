package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.Authority;
import com.gdu_springboot.spring_boot_demo.service.AuthorityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorityRestController {
    private AuthorityService AuthorityService;

    public AuthorityRestController(AuthorityService AuthorityService) {
        this.AuthorityService = AuthorityService;
    }

    @GetMapping("/authority")
    public List<Authority> getAuthoritys() {
        return AuthorityService.findAll();
    }
    @GetMapping("/authority/{id}")
    public Authority getAuthority(@PathVariable int id) {
        return AuthorityService.findById(id);
    }
    @PostMapping("/authority")
    public Authority addAuthority(@RequestBody Authority Authority) {
        AuthorityService.save(Authority);
        return Authority;
    }
    @PutMapping("/authority")
    public Authority updateAuthority(@RequestBody Authority Authority) {
        Authority oldAuthority = AuthorityService.save(Authority);
        return oldAuthority;
    }
    @DeleteMapping("/authority/{id}")
    public String deleteAuthority(@PathVariable int id) {
        AuthorityService.deleteById(id);
        return "Delete success has id = "+ id;
    }
}
