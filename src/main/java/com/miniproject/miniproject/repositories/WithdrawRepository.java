package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {
}
