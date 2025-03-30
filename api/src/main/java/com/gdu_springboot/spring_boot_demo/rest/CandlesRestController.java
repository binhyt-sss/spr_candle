package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.Candles;
import com.gdu_springboot.spring_boot_demo.service.CandlesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandlesRestController {
    private CandlesService CandlesService;

    public CandlesRestController(CandlesService CandlesService) {
        this.CandlesService = CandlesService;
    }

    @GetMapping("/candles")
    public List<Candles> getCandless() {
        return CandlesService.findAll();
    }
    @GetMapping("/candles/{id}")
    public Candles getCandles(@PathVariable int id) {
        return CandlesService.findById(id);
    }
    @PostMapping("/candles")
    public Candles addCandles(@RequestBody Candles Candles) {
        CandlesService.save(Candles);
        return Candles;
    }
    @PutMapping("/candles")
    public Candles updateCandles(@RequestBody Candles Candles) {
        Candles oldCandles = CandlesService.save(Candles);
        return oldCandles;
    }
    @DeleteMapping("/candles/{id}")
    public String deleteCandles(@PathVariable int id) {
        CandlesService.deleteById(id);
        return "Delete success has id = "+ id;
    }
}
