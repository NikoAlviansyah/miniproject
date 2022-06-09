package com.miniproject.miniproject.dtos.transfer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransferDto {

    private final Integer transferId;
    private final Long fund;
    private final String note;
    private final LocalDate date;
    private final String status;
    private final String senderUser;
    private final String recipientUser;
}
