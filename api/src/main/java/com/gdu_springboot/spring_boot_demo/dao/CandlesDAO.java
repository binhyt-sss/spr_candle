package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Candles;

import java.util.List;

public interface CandlesDAO {
    Candles getCandlesById(int id);
    List<Candles> getAllAuthorities();
    Candles addCandles(Candles Candles);
    void deleteCandles(int id);
}
