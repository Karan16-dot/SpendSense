package com.spendsense.category.repository;

import com.spendsense.category.entity.Category;
import com.spendsense.category.entity.CategoryType;
import com.spendsense.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByUser(User user);

    List<Category> findByUserAndType(User user, CategoryType type);

    Optional<Category> findByUserAndNameIgnoreCase(User user, String name);

    boolean existsByUserAndNameIgnoreCase(User user, String name);

    List<Category> findByUserOrderByNameAsc(User user);

    List<Category> findByUserAndTypeOrderByNameAsc(User user, CategoryType type);
}