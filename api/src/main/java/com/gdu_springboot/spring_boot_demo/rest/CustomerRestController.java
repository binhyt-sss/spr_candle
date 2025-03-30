package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.Customer;
import com.gdu_springboot.spring_boot_demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private CustomerService CustomerService;

    public CustomerRestController(CustomerService CustomerService) {
        this.CustomerService = CustomerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return CustomerService.findAll();
    }
    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return CustomerService.findById(id);
    }
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer Customer) {
        CustomerService.save(Customer);
        return Customer;
    }
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer Customer) {
        Customer oldCustomer = CustomerService.save(Customer);
        return oldCustomer;
    }
    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        CustomerService.deleteById(id);
        return "Delete success has id = "+ id;
    }
}
