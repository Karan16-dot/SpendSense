package com.spendsense.user.entity;

import com.spendsense.category.entity.Category;
import com.spendsense.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import com.spendsense.user.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private Role role = Role.ROLE_USER;

    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();
}