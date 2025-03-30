package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.dao.CustomerDAO;
import com.gdu_springboot.spring_boot_demo.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    private CustomerDAO CustomerDAO;

    public CustomerServiceImp(CustomerDAO CustomerDAO) {
        this.CustomerDAO = CustomerDAO;
    }

    @Override
    public List<Customer> findAll() {
        return CustomerDAO.getAllCustomers();
    }

    @Override
    public Customer findById(int id) {
        return CustomerDAO.getCustomerById(id);
    }

    @Override
    public Customer save(Customer Customer) {
        CustomerDAO.addCustomer(Customer);
        return Customer;
    }

    @Override
    public void deleteById(int id) {
        CustomerDAO.deleteCustomer(id);

    }
}
