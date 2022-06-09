package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    @Query(value = """
            SELECT *
            FROM [Transfer]
            WHERE [SenderUserId] = :senderUserId
            """, nativeQuery = true)
    List<Transfer> getTransferHistoryBySenderUserId(@Param("senderUserId") String senderUserId);

    @Query(value = """
            SELECT *
            FROM [Transfer]
            WHERE [RecipientUserId] = :recipientUserId
            """, nativeQuery = true)
    List<Transfer> getTransferHistoryByRecipientUserId(@Param("recipientUserId") String recipientUserId);
}
