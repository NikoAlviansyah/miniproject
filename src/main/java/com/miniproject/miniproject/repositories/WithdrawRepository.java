package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {

    @Query(value = """
            SELECT *
            FROM [Withdraw]
            WHERE [UserId] = :userId
            """, nativeQuery = true)
    List<Withdraw> getWithdrawHistoryByUserId(@Param("userId") String userId);
}
