package com.the_dapda.domain.category.repository;

import com.the_dapda.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
