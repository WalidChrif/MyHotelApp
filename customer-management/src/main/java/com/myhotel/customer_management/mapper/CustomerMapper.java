package com.myhotel.customer_management.mapper;

import com.myhotel.customer_management.entity.Customer;
import com.myhotel.customer_management.model.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    
    public Customer convertToEntity(CustomerDTO customerDTO) {
        Customer theCustomer = new Customer();
        theCustomer.setId(customerDTO.getId());
        theCustomer.setName(customerDTO.getName());
        theCustomer.setEmail(customerDTO.getEmail());
        return theCustomer;
    }

    public CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO theCustomer = new CustomerDTO();
        theCustomer.setId(customer.getId());
        theCustomer.setName(customer.getName());
        theCustomer.setEmail(customer.getEmail());
        return theCustomer;
    }

    public List<CustomerDTO> convertToDTOs(List<Customer> customers) {
        return customers.stream()
//                .map(customer -> convertToDTO(customer))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}