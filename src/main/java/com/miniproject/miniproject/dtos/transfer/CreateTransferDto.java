package com.miniproject.miniproject.dtos.transfer;

import lombok.Data;

@Data
public class CreateTransferDto {

    private final Long fund;
    private final String note;
    private final String senderAccountNumber;
    private final String recipientAccountNumber;
}
