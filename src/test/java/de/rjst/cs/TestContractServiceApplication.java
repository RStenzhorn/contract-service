package de.rjst.cs;

import org.springframework.boot.SpringApplication;

public class TestContractServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(ContractServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
