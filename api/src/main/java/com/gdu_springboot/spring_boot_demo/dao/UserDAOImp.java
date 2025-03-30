package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {
    private EntityManager em;

    public UserDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("select s from User s", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public User addUser(User User) {
        return em.merge(User);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        em.remove(em.find(User.class, id));
    }
}
