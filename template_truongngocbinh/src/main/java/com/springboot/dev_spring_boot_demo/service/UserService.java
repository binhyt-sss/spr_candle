package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String username);
    User save(User user);  // Removed String authority parameter
    void deleteById(String username);
    boolean existsById(String username);
}