package de.rjst.cs;

import de.rjst.cs.adapter.CustomerDto;
import de.rjst.cs.adapter.CustomerService;
import de.rjst.cs.api.ContractDto;
import de.rjst.cs.api.CreateContractDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("contracts")
public class ContractController {

    private final CustomerService customerService;

    @PostMapping
    public ContractDto createContract(@RequestBody CreateContractDto contractDto) {
        String customerId = contractDto.getCustomerId();
        CustomerDto customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        return new ContractDto();
    }

}
