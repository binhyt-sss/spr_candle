package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Candles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CandlesDAOImp implements CandlesDAO {
    private EntityManager em;

    public CandlesDAOImp(EntityManager em) {
        this.em = em;
    }


    @Override
    public Candles getCandlesById(int id) {
        return em.find(Candles.class, id);
    }

    @Override
    public List<Candles> getAllAuthorities() {
        TypedQuery<Candles> query = em.createQuery("select s from Candles s", Candles.class);
        return query.getResultList();    }

    @Override
    public Candles addCandles(Candles Candles) {
        return em.merge(Candles);
    }

    @Override
    public void deleteCandles(int id) {
        em.remove(em.find(Candles.class, id));

    }
}
