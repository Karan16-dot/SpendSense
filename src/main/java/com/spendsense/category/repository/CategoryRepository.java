package com.spendsense.category.repository;

import com.spendsense.category.entity.Category;
import com.spendsense.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsByNameIgnoreCase(String name);

    List<Category> findByUserOrIsDefaultTrue(User user);

    Optional<Category> findByIdAndUser(UUID id, User user);

}