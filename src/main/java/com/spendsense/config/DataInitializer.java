package com.spendsense.config;

import com.spendsense.expense.entity.Category;
import com.spendsense.expense.repository.CategoryRepository;
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

                Category.builder().name("Food").build(),

                Category.builder().name("Transport").build(),

                Category.builder().name("Shopping").build(),

                Category.builder().name("Bills").build(),

                Category.builder().name("Health").build(),

                Category.builder().name("Entertainment").build(),

                Category.builder().name("Education").build(),

                Category.builder().name("Investment").build(),

                Category.builder().name("Travel").build(),

                Category.builder().name("Others").build()
        );

        categoryRepository.saveAll(categories);

        System.out.println("Default categories inserted.");
    }
}