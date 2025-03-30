package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityDAOImp implements AuthorityDAO {
    private EntityManager em;

    public AuthorityDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public Authority getAuthorityById(int id) {
        return em.find(Authority.class, id);
    }

    @Override
    public List<Authority> getAllAuthoritys() {
        TypedQuery<Authority> query = em.createQuery("select s from Authority s", Authority.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Authority addAuthority(Authority Authority) {
        return em.merge(Authority);
    }

    @Override
    @Transactional
    public void deleteAuthority(int id) {
        em.remove(em.find(Authority.class, id));
    }
}
