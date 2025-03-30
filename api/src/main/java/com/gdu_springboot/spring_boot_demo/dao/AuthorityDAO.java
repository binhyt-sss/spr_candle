package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Authority;

import java.util.List;

public interface AuthorityDAO {
    Authority getAuthorityById(int id);
    List<Authority> getAllAuthoritys();
    Authority addAuthority(Authority authority);
    void deleteAuthority(int id);
}
