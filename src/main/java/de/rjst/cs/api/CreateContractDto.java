package de.rjst.cs.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateContractDto {

    private String customerId;
    private String productId;
    private LocalDate startDate;
    private LocalDate endDate;

}
