package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.entity.User;

import java.util.List;


public interface UserService {
    List<User> findAll();
    User findById(int id);
    User save(User User);
    void deleteById(int id);
}
