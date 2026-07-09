package com.spendsense.expense.repository;

import com.spendsense.expense.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository
        extends JpaRepository<Category, UUID> {
}