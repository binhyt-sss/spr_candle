package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.User;

import java.util.List;

public interface UserDAO {
    User getUserById(int id);
    List<User> getAllUsers();
    User addUser(User user);
    void deleteUser(int id);
}
