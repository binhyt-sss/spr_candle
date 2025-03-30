package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.entity.Authority;

import java.util.List;


public interface AuthorityService {
    List<Authority> findAll();
    Authority findById(int id);
    Authority save(Authority Authority);
    void deleteById(int id);
}
