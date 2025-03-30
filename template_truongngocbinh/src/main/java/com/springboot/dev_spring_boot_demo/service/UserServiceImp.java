package com.springboot.dev_spring_boot_demo.service;

//import com.springboot.dev_spring_boot_demo.dao.AuthorityDAO;
import com.springboot.dev_spring_boot_demo.dao.UserDAO;
//import com.springboot.dev_spring_boot_demo.entity.Authority;
import com.springboot.dev_spring_boot_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(String username) {
        return userDAO.findById(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteById(String username) {
        User user = userDAO.findById(username);
        if (user != null) {
            userDAO.deleteById(username);
        }
    }

    @Override
    public boolean existsById(String username) {
        return userDAO.findById(username) != null;
    }
}

