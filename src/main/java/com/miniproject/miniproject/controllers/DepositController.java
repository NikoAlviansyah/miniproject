package com.miniproject.miniproject.controllers;

import com.miniproject.miniproject.dtos.deposit.CreateDepositDto;
import com.miniproject.miniproject.dtos.deposit.DepositDto;
import com.miniproject.miniproject.responses.RestResponse;
import com.miniproject.miniproject.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("deposit")
public class DepositController {

    private DepositService service;

    @Autowired
    public DepositController(DepositService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<DepositDto>>> getAllDepositHistory(){
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllDepositHistory(),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<DepositDto>> createDeposit(@RequestBody CreateDepositDto newDeposit){
        DepositDto depositDto = service.createDeposit(newDeposit);

        if (depositDto.getStatus().equals("Successfully")) {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            depositDto,
                            "Deposit successfully.",
                            "200"),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            depositDto,
                            "Deposit failed.",
                            "200"),
                    HttpStatus.CREATED);
        }
    }
}
