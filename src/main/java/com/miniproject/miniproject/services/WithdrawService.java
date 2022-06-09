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
import java.util.stream.Stream;

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
        List<WithdrawDto> withdrawDtos = new ArrayList<>();

        Stream<Withdraw> withdraws = withdrawRepository.findAll().stream();

        withdraws.forEach(
                (withdraw) -> {
                    withdrawDtos.add(
                            new WithdrawDto(
                                    withdraw.getWithdrawId(),
                                    withdraw.getFund(),
                                    withdraw.getDate(),
                                    withdraw.getStatus(),
                                    withdraw.getUser().getAccountNumber()
                            )
                    );
                }
        );

        return withdrawDtos;
    }

    public List<WithdrawDto> getWithdrawHistoryByUserId(String userId) {
        List<WithdrawDto> withdrawDtos = new ArrayList<>();

        Stream<Withdraw> withdraws = withdrawRepository.getWithdrawHistoryByUserId(userId).stream();

        withdraws.forEach(
                (withdraw) -> {
                    withdrawDtos.add(
                            new WithdrawDto(
                                    withdraw.getWithdrawId(),
                                    withdraw.getFund(),
                                    withdraw.getDate(),
                                    withdraw.getStatus(),
                                    withdraw.getUser().getAccountNumber()
                            )
                    );
                }
        );

        return withdrawDtos;
    }

    public WithdrawDto createWithdraw(CreateWithdrawDto newWithdraw) {
        User customer = userRepository.findCustomerByAccountNumber(newWithdraw.getAccountNumber(), true)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setFund(customer.getFund() - newWithdraw.getFund());

        if (newWithdraw.getFund() > 0 && customer.getFund() >= 0) {
            Withdraw withdraw = new Withdraw(
                    newWithdraw.getFund(),
                    LocalDate.now(),
                    "Successfully",
                    customer
            );

            withdrawRepository.save(withdraw);

            return new WithdrawDto(withdraw.getWithdrawId(),
                    withdraw.getFund(),
                    withdraw.getDate(),
                    withdraw.getStatus(),
                    withdraw.getUser().getAccountNumber());
        } else {
            customer.setFund(customer.getFund() + newWithdraw.getFund());

            Withdraw withdraw = new Withdraw(
                    newWithdraw.getFund(),
                    LocalDate.now(),
                    "Failed",
                    customer
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
