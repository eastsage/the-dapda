package com.the_dapda.domain.category.repository;

import com.the_dapda.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title); // title로 Category 검색
}

