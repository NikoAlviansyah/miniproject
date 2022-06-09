package com.miniproject.miniproject.controllers;

import com.miniproject.miniproject.dtos.transfer.CreateTransferDto;
import com.miniproject.miniproject.dtos.transfer.TransferDto;
import com.miniproject.miniproject.responses.RestResponse;
import com.miniproject.miniproject.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transfer")
public class TransferController {

    private TransferService service;

    @Autowired
    public TransferController(TransferService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<TransferDto>>> getAllTransferHistory() {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllTransferHistory(),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<TransferDto>> createDeposit(@RequestBody CreateTransferDto newTransfer) {
        TransferDto transferDto = service.createTransfer(newTransfer);

        if (transferDto.getStatus().equals("Successfully")) {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            transferDto,
                            "Transfer successfully.",
                            "200"),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            transferDto,
                            "Transfer failed.",
                            "200"),
                    HttpStatus.CREATED);
        }
    }
}
