package com.spendsense.config;

import com.spendsense.category.entity.Category;
import com.spendsense.category.entity.CategoryType;
import com.spendsense.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {

        if (categoryRepository.count() > 0) {
            return;
        }

        List<Category> categories = List.of(

                Category.builder().name("Food").icon("restaurant").color("#FF9800").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Transport").icon("flight").color("#2196F3").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Shopping").icon("shopping_bag").color("#9C27B0").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Bills").icon("receipt_long").color("#F44336").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Health").icon("medical_services").color("#009688").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Entertainment").icon("movie").color("#673AB7").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Education").icon("school").color("#9C27B0").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Investment").icon("trending_up").color("#4CAF50").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Travel").icon("flight").color("#00BCD4").type(CategoryType.EXPENSE).isDefault(true).build(),

                Category.builder().name("Others").icon("more_horiz").color("#9E9E9E").type(CategoryType.EXPENSE).isDefault(true).build()
        );

        categoryRepository.saveAll(categories);

        System.out.println("Default categories inserted.");
    }
}