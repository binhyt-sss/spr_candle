package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.dao.AuthorityDAO;
import com.gdu_springboot.spring_boot_demo.entity.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImp implements AuthorityService {
    private AuthorityDAO AuthorityDAO;

    public AuthorityServiceImp(AuthorityDAO AuthorityDAO) {
        this.AuthorityDAO = AuthorityDAO;
    }

    @Override
    public List<Authority> findAll() {
        return AuthorityDAO.getAllAuthoritys();
    }

    @Override
    public Authority findById(int id) {
        return AuthorityDAO.getAuthorityById(id);
    }

    @Override
    public Authority save(Authority Authority) {
        AuthorityDAO.addAuthority(Authority);
        return Authority;
    }

    @Override
    public void deleteById(int id) {
        AuthorityDAO.deleteAuthority(id);
    }
}
