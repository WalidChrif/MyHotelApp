package com.myhotel.customer_management.service;

import com.myhotel.customer_management.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAllCustomers();
    CustomerDTO findCustomerById(Long id);
    CustomerDTO findCustomerByName(String name);
    CustomerDTO saveCostumer(CustomerDTO customerDTO);
    CustomerDTO updateCostumer(Long id, CustomerDTO customerDTO);
}
