package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.Candles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CandlesDAOImp implements CandlesDAO {
    private EntityManager em;

    @Autowired
    public CandlesDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Candles> findAll() {
        TypedQuery<Candles> query = em.createQuery("from Candles", Candles.class);
        return query.getResultList();
    }

    @Override
    public Candles findById(int id) {
        return em.find(Candles.class, id);
    }

    @Override
    @Transactional
    public Candles save(Candles candles) {
        Candles saved = em.merge(candles);
        return saved;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Candles candles = em.find(Candles.class, id);
        em.remove(candles);
    }
}