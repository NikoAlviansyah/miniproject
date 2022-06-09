package com.miniproject.miniproject.dtos.deposit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DepositDto {

    private final Integer depositId;
    private final Long fund;
    private final LocalDate date;
    private final String status;
    private final String accountNumber;
}
