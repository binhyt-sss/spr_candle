package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.Candles;
import java.util.List;

public interface CandlesDAO {
    List<Candles> findAll();
    Candles findById(int id);
    Candles save(Candles candles);
    void deleteById(int id);
}