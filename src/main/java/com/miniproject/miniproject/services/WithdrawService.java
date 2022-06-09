package com.miniproject.miniproject.services;

import com.miniproject.miniproject.dtos.withdraw.CreateWithdrawDto;
import com.miniproject.miniproject.dtos.withdraw.WithdrawDto;
import com.miniproject.miniproject.exceptions.EntityNotFoundException;
import com.miniproject.miniproject.models.User;
import com.miniproject.miniproject.models.Withdraw;
import com.miniproject.miniproject.repositories.UserRepository;
import com.miniproject.miniproject.repositories.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawService {

    private WithdrawRepository withdrawRepository;
    private UserRepository userRepository;

    @Autowired
    public WithdrawService(WithdrawRepository withdrawRepository, UserRepository userRepository) {
        this.withdrawRepository = withdrawRepository;
        this.userRepository = userRepository;
    }

    public List<WithdrawDto> getAllWithdrawHistory() {
        List<WithdrawDto> withdrawDtoList = new ArrayList<>();

        List<Withdraw> withdrawList = withdrawRepository.findAll();

        for (Withdraw withdraw : withdrawList) {
            withdrawDtoList.add(
                    new WithdrawDto(
                            withdraw.getWithdrawId(),
                            withdraw.getFund(),
                            withdraw.getDate(),
                            withdraw.getStatus(),
                            withdraw.getUser().getAccountNumber()
                    )
            );
        }

        return withdrawDtoList;
    }

    public WithdrawDto createWithdraw(CreateWithdrawDto newWithdraw) {
        User user = userRepository.findCustomerByAccountNumber(newWithdraw.getAccountNumber(), true)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        user.setFund(user.getFund() - newWithdraw.getFund());

        if (newWithdraw.getFund() > 0 && user.getFund() >= 0) {
            Withdraw withdraw = new Withdraw(
                    newWithdraw.getFund(),
                    LocalDate.now(),
                    "Successfully",
                    user
            );

            withdrawRepository.save(withdraw);

            return new WithdrawDto(withdraw.getWithdrawId(),
                    withdraw.getFund(),
                    withdraw.getDate(),
                    withdraw.getStatus(),
                    withdraw.getUser().getAccountNumber());
        } else {
            user.setFund(user.getFund() + newWithdraw.getFund());

            Withdraw withdraw = new Withdraw(
                    newWithdraw.getFund(),
                    LocalDate.now(),
                    "Failed",
                    user
            );

            withdrawRepository.save(withdraw);

            return new WithdrawDto(withdraw.getWithdrawId(),
                    withdraw.getFund(),
                    withdraw.getDate(),
                    withdraw.getStatus(),
                    withdraw.getUser().getAccountNumber());
        }
    }
}
