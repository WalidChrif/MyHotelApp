package com.myhotel.customer_management.repository;

import com.myhotel.customer_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByName(String name);
}
