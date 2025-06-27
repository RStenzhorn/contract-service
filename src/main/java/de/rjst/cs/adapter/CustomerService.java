package de.rjst.cs.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer", path = "customers")
public interface CustomerService {

    @GetMapping("{id}")
    CustomerDto getCustomerById(@PathVariable("id") String id);

    @PostMapping
    CustomerDto createCustomer(@RequestBody CreateCustomerDto createCustomerDto);
}

