package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Customer;
import com.springboot.dev_spring_boot_demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list-customer")
    public String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "list-customer";
    }

    @GetMapping("/customer-form-insert")
    public String customerFormInsert(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form-insert";
    }
    
    // Support URL that uses ID parameter for editing via insert form
    @GetMapping(value = "/customer-form-insert", params = "id")
    public String customerFormInsertWithId(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            customer = new Customer();
        }
        model.addAttribute("customer", customer);
        return "customer-form-insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            if (customer.getId() == 0) {
                return "customer-form-insert";
            } else {
                return "customer-form-update";
            }
        }
        customerService.save(customer);
        return "redirect:/customer/list-customer";
    }

    // Handle update with the path in list-customer.html
    @GetMapping("/customer-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer-form-update";
    }
    
    // Support the old path for compatibility
    @GetMapping("/update")
    public String updateCustomer(@RequestParam("id") int id, Model model) {
        return formUpdate(id, model);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        customerService.deleteById(id);
        return "redirect:/customer/list-customer";
    }
}