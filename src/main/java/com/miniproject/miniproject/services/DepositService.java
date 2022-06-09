package com.miniproject.miniproject.services;

import com.miniproject.miniproject.dtos.deposit.CreateDepositDto;
import com.miniproject.miniproject.dtos.deposit.DepositDto;
import com.miniproject.miniproject.exceptions.EntityNotFoundException;
import com.miniproject.miniproject.models.Deposit;
import com.miniproject.miniproject.models.User;
import com.miniproject.miniproject.repositories.DepositRepository;
import com.miniproject.miniproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DepositService {

    private DepositRepository depositRepository;
    private UserRepository userRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, UserRepository userRepository) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }

    public List<DepositDto> getAllDepositHistory() {
        List<DepositDto> depositDtos = new ArrayList<>();

        Stream<Deposit> deposits = depositRepository.findAll().stream();

        deposits.forEach(
                (deposit) -> {
                    depositDtos.add(
                            new DepositDto(
                                    deposit.getDepositId(),
                                    deposit.getFund(),
                                    deposit.getDate(),
                                    "Successfully",
                                    deposit.getUser().getAccountNumber()
                            )
                    );
                }
        );

        return depositDtos;
    }

    public List<DepositDto> getDepositHistoryByUserId(String userId) {
        List<DepositDto> depositDtos = new ArrayList<>();

        Stream<Deposit> deposits = depositRepository.getDepositHistoryByUserId(userId).stream();

        deposits.forEach(
                (deposit) -> {
                    depositDtos.add(
                            new DepositDto(
                                    deposit.getDepositId(),
                                    deposit.getFund(),
                                    deposit.getDate(),
                                    "Successfully",
                                    deposit.getUser().getAccountNumber()
                            )
                    );
                }
        );

        return depositDtos;
    }

    public DepositDto createDeposit(CreateDepositDto newDeposit) {
        User customer = userRepository.findCustomerByAccountNumber(newDeposit.getAccountNumber(), true)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));

        customer.setFund(customer.getFund() + newDeposit.getFund());

        if (newDeposit.getFund() > 0) {
            Deposit deposit = new Deposit(
                    newDeposit.getFund(),
                    LocalDate.now(),
                    customer
            );

            depositRepository.save(deposit);

            return new DepositDto(deposit.getDepositId(),
                    deposit.getFund(),
                    deposit.getDate(),
                    "Successfully",
                    deposit.getUser().getAccountNumber());
        } else {
            return new DepositDto(null,
                    newDeposit.getFund(),
                    LocalDate.now(),
                    "Failed",
                    newDeposit.getAccountNumber());
        }
    }
}
