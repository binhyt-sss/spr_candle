package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.dao.*;
import com.springboot.dev_spring_boot_demo.entity.Candles;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandlesServiceImp implements CandlesService {
    private CandlesDAO candlesDAO;

    @Autowired
    public CandlesServiceImp(CandlesDAO candlesDAO) {
        this.candlesDAO = candlesDAO;
    }

    @Override
    public List<Candles> findAll() {
        return candlesDAO.findAll();
    }

    @Override
    public Candles findById(int id) {
        return candlesDAO.findById(id);
    }

    @Override
    public Candles save(Candles candles) {
        return candlesDAO.save(candles);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        candlesDAO.deleteById(id);
    }
}