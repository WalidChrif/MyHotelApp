package com.myhotel.customer_management.controller;


import com.myhotel.customer_management.entity.Customer;
import com.myhotel.customer_management.model.CustomerDTO;
import com.myhotel.customer_management.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO findCustomerById(@PathVariable Long id) throws InterruptedException {
//        Thread.sleep(5000);
        return customerService.findCustomerById(id);
    }

    @GetMapping("/name/{name}")
    public CustomerDTO findCustomerByName(@PathVariable String name) {
        return customerService.findCustomerByName(name);
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCostumer(customerDTO);
    }

    @PutMapping
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCostumer(id, customerDTO);
    }


}
