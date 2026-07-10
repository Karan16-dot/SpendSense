package com.spendsense.income.entity;

import com.spendsense.common.entity.BaseEntity;
import com.spendsense.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "income")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Income extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable =false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncomeSource source;

    @Column(nullable = false)
    private LocalDate receivedDate;

    @Column(length = 500)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @Column(nullable = false)
    private Boolean deleted = false;

}