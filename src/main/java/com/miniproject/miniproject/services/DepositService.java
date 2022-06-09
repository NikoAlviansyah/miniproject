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
        List<DepositDto> depositDtoList = new ArrayList<>();

        List<Deposit> depositList = depositRepository.findAll();

        for (Deposit deposit : depositList) {
            depositDtoList.add(
                    new DepositDto(
                            deposit.getDepositId(),
                            deposit.getFund(),
                            deposit.getDate(),
                            "Successfully",
                            deposit.getUser().getAccountNumber()
                    )
            );
        }

        return depositDtoList;
    }

    public DepositDto createDeposit(CreateDepositDto newDeposit) {
        User user = userRepository.findCustomerByAccountNumber(newDeposit.getAccountNumber(), true)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));

        user.setFund(user.getFund() + newDeposit.getFund());

        if (newDeposit.getFund() > 0) {
            Deposit deposit = new Deposit(
                    newDeposit.getFund(),
                    LocalDate.now(),
                    user
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
