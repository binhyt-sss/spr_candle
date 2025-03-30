package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.dao.UserDAO;
import com.gdu_springboot.spring_boot_demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserDAO UserDAO;

    public UserServiceImp(UserDAO UserDAO) {
        this.UserDAO = UserDAO;
    }

    @Override
    public List<User> findAll() {
        return UserDAO.getAllUsers();
    }

    @Override
    public User findById(int id) {
        return UserDAO.getUserById(id);
    }

    @Override
    public User save(User User) {
        UserDAO.addUser(User);
        return User;
    }

    @Override
    public void deleteById(int id) {
        UserDAO.deleteUser(id);

    }
}
