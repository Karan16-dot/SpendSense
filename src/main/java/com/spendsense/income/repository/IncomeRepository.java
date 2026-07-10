package com.spendsense.income.repository;

import com.spendsense.income.entity.Income;
import com.spendsense.income.entity.IncomeSource;
import com.spendsense.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface IncomeRepository extends JpaRepository<Income, UUID> {

    Page<Income> findByUserAndDeletedFalse(
            User user,
            Pageable pageable
    );

    Optional<Income> findByIdAndUserAndDeletedFalse(
            UUID id,
            User user
    );

    Page<Income> findByUserAndSourceAndDeletedFalse(
            User user,
            IncomeSource source,
            Pageable pageable
    );

    Page<Income> findByUserAndReceivedDateBetweenAndDeletedFalse(
            User user,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    @Query("""
            SELECT COALESCE(SUM(i.amount), 0)
            FROM Income i
            WHERE i.user = :user
              AND i.deleted = false
              AND YEAR(i.receivedDate) = :year
              AND MONTH(i.receivedDate) = :month
            """)
    BigDecimal calculateMonthlyIncome(
            @Param("user") User user,
            @Param("year") int year,
            @Param("month") int month
    );

    @Query("""
            SELECT COALESCE(SUM(i.amount), 0)
            FROM Income i
            WHERE i.user = :user
              AND i.deleted = false
            """)
    BigDecimal calculateTotalIncome(
            @Param("user") User user
    );

    long countByUserAndDeletedFalse(User user);

    @Query("""
SELECT COALESCE(SUM(i.amount),0)
FROM Income i
WHERE i.user = :user
AND i.deleted = false
AND i.receivedDate BETWEEN :startDate AND :endDate
""")
    BigDecimal calculateTotalIncomeBetween(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}