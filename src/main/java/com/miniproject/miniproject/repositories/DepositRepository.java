package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    @Query(value = """
            SELECT *
            FROM [Deposit]
            WHERE [UserId] = :userId
            """, nativeQuery = true)
    List<Deposit> getDepositHistoryByUserId(@Param("userId") String userId);
}
