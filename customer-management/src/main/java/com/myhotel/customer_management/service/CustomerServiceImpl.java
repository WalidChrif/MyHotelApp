package com.myhotel.customer_management.service;


import com.myhotel.customer_management.entity.Customer;
import com.myhotel.customer_management.mapper.CustomerMapper;
import com.myhotel.customer_management.model.CustomerDTO;
import com.myhotel.customer_management.repository.CustomerRepo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerMapper.convertToDTOs(customerRepo.findAll());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerMapper
                .convertToDTO(customerRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    @Override
    public CustomerDTO findCustomerByName(String name) {
        return customerMapper
                .convertToDTO(customerRepo.findCustomerByName(name)
                        .orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    @Override
    public CustomerDTO saveCostumer(CustomerDTO customerDTO) {
        kafkaTemplate.send("customerTopic", "A customer named "+ customerDTO.getName() + " was signed to the hotel");
        return customerMapper.convertToDTO(customerRepo.save(customerMapper.convertToEntity(customerDTO)));
    }

    @Override
    public CustomerDTO updateCostumer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customerMapper.convertToDTO(customerRepo.save(customer));
    }
}
