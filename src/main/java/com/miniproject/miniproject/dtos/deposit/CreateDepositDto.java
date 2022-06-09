package com.miniproject.miniproject.dtos.deposit;

import lombok.Data;

@Data
public class CreateDepositDto {

    private final Long fund;
    private final String accountNumber;
}
