package com.miniproject.miniproject.dtos.withdraw;

import lombok.Data;

@Data
public class CreateWithdrawDto {

    private final Long fund;
    private final String accountNumber;
}
