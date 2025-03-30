package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.entity.Candles;

import java.util.List;


public interface CandlesService {
    List<Candles> findAll();
    Candles findById(int id);
    Candles save(Candles Candles);
    void deleteById(int id);
}
