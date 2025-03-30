package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.Candles;
import java.util.List;

public interface CandlesService {
    List<Candles> findAll();
    Candles findById(int id);
    Candles save(Candles candles);
    void deleteById(int id);
}