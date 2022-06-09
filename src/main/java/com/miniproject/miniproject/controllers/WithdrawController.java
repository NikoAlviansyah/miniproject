package com.miniproject.miniproject.controllers;

import com.miniproject.miniproject.dtos.withdraw.CreateWithdrawDto;
import com.miniproject.miniproject.dtos.withdraw.WithdrawDto;
import com.miniproject.miniproject.responses.RestResponse;
import com.miniproject.miniproject.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("withdraw")
public class WithdrawController {

    private WithdrawService service;

    @Autowired
    public WithdrawController(WithdrawService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<WithdrawDto>>> getAllWithdrawHistory() {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllWithdrawHistory(),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<RestResponse<List<WithdrawDto>>> getDepositHistoryByUserId(@PathVariable String userId){
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getWithdrawHistoryByUserId(userId),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<WithdrawDto>> createWithdraw(@RequestBody CreateWithdrawDto newWithdraw) {
        WithdrawDto withdrawDto = service.createWithdraw(newWithdraw);

        if (withdrawDto.getStatus().equals("Successfully")) {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            withdrawDto,
                            "Withdraw successfully.",
                            "200"),
                    HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(
                    new RestResponse<>(
                            withdrawDto,
                            "Withdraw failed.",
                            "200"),
                    HttpStatus.CREATED);
        }
    }
}
