package com.the_dapda.domain.category.repository;

import com.the_dapda.domain.category.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.prompt = :prompt WHERE c.id = :categoryId")
    void updatePromptById(Long categoryId, String prompt);
}
