package com.miniproject.miniproject.repositories;

import com.miniproject.miniproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE Role = :role
            AND IsActive = :isActive
            """, nativeQuery = true)
    List<User> getAllAdminOrCustomer(@Param("role") String role, @Param("isActive") Boolean isActive);

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE (FirstName LIKE %:name%
            AND Role = :role
            AND IsActive = :isActive)
            OR (LastName LIKE %:name%
            AND Role = :role
            AND IsActive = :isActive)
            """, nativeQuery = true)
    List<User> getAllAdminOrCustomerByName(@Param("name") String name,
                                           @Param("role") String role,
                                           @Param("isActive") Boolean isActive);

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE City LIKE %:city%
            AND Role = :role
            AND IsActive = :isActive
            """, nativeQuery = true)
    List<User> getAllAdminOrCustomerByCity(@Param("city") String city,
                                           @Param("role") String role,
                                           @Param("isActive") Boolean isActive);

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE UserId = :userId
            AND IsActive = :isActive
            """, nativeQuery = true)
    Optional<User> getAdminOrCustomerByUserId(@Param("userId") String userId,
                                              @Param("isActive") Boolean isActive);

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE AccountNumber = :accountNumber
            """, nativeQuery = true)
    Optional<User> findCustomerByAccountNumberForNew(@Param("accountNumber") String accountNumber);

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE AccountNumber = :accountNumber
            AND IsActive = :isActive
            """, nativeQuery = true)
    Optional<User> findCustomerByAccountNumber(@Param("accountNumber") String accountNumber,
                                               @Param("isActive") Boolean isActive);

    @Query(value = """
            SELECT *
            FROM [User]
            WHERE AccountNumber = :accountNumber
            AND AccountNumber != :senderAccountNumber
            AND IsActive = :isActive
            """, nativeQuery = true)
    Optional<User> findCustomerRecipientByAccountNumber(@Param("accountNumber") String accountNumber,
                                                        @Param("senderAccountNumber") String senderAccountNumber,
                                                        @Param("isActive") Boolean isActive);
}
