package com.myhotel.booking_management.proxies;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-management")
public interface CustomerProxy {

    @GetMapping("/api/v1/customers/{id}")
    public Object getCustomerById(@PathVariable Long id);
}
