package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Candles;
import com.springboot.dev_spring_boot_demo.entity.Customer;
import com.springboot.dev_spring_boot_demo.service.CandlesService;
import com.springboot.dev_spring_boot_demo.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private CandlesService candlesService;
    private CustomerService customerService;

    public HomeController(CandlesService candlesService, CustomerService customerService) {
        this.candlesService = candlesService;
        this.customerService = customerService;
    }

    @GetMapping("/candles-home")
    public String home() {
        return "index";  // Trỏ đến templates/index.html
    }
    @GetMapping("ship")
    public String ship() {
        return "shipment";
    }

    @GetMapping("/order")
    public String showOrderPage(@RequestParam(value = "plan", required = false) String plan, Model model) {
        model.addAttribute("candles", candlesService.findAll());
        if (plan != null) {
            model.addAttribute("selectedPlan", plan);
        }
        return "order";
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


    @GetMapping("/cus")
    public String showCustomerPage(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "cus";
    }
    
    @GetMapping("/candle-detail")
    public String showCandleDetail(@RequestParam("id") int id, Model model) {
        Candles candle = candlesService.findById(id);
        if (candle == null) {
            return "redirect:/order";
        }
        model.addAttribute("candle", candle);
        return "candle-detail";
    }
}
