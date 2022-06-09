package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
