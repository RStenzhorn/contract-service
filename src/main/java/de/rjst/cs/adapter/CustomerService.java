package de.rjst.cs.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer")
public interface CustomerService {

    @GetMapping("customers/{id}")
    CustomerDto getCustomerById(@PathVariable("id") String id);

}

