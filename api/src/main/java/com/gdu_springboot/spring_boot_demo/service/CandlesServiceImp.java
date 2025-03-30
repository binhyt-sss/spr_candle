package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.dao.CandlesDAO;
import com.gdu_springboot.spring_boot_demo.entity.Candles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandlesServiceImp implements CandlesService {
    private CandlesDAO CandlesDAO;

    public CandlesServiceImp(CandlesDAO CandlesDAO) {
        this.CandlesDAO = CandlesDAO;
    }

    @Override
    public List<Candles> findAll() {
        return CandlesDAO.getAllAuthorities();
    }

    @Override
    public Candles findById(int id) {
        return CandlesDAO.getCandlesById(id);
    }

    @Override
    public Candles save(Candles Candles) {
        CandlesDAO.addCandles(Candles);
        return Candles;
    }

    @Override
    public void deleteById(int id) {
        CandlesDAO.deleteCandles(id);

    }
}
