package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {
}
