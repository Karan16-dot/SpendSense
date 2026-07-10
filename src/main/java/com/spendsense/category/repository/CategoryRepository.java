package com.spendsense.category.repository;

import com.spendsense.category.entity.Category;
import com.spendsense.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Category> findByIdAndUser(UUID id, User user);

    List<Category> findByUserOrIsDefaultTrue(User user);

}