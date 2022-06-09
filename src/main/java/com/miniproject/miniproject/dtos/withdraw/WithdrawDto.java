package com.miniproject.miniproject.dtos.withdraw;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WithdrawDto {

    private final Integer withdrawId;
    private final Long fund;
    private final LocalDate date;
    private final String status;
    private final String accountNumber;
}
