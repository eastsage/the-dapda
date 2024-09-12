package com.the_dapda.domain.category.service.impl;

import com.the_dapda.domain.category.dto.response.CategoryResponse;
import com.the_dapda.domain.category.entity.Category;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.category.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();
    }

    @Override
    public boolean savePrompt(String title, String prompt) {
        try {
            // title로 기존 Category 찾기
            Optional<Category> existingCategory = categoryRepository.findByTitle(title);

            if (existingCategory.isPresent()) {
                // 기존 Category가 있을 경우 prompt 업데이트
                Category category = existingCategory.get();
                category.setPrompt(prompt); // prompt 값 업데이트
                categoryRepository.save(category); // 업데이트된 카테고리 저장
            } else {
                // 존재하지 않을 경우 새로 생성하여 저장
                Category newCategory = new Category(title, prompt);
                categoryRepository.save(newCategory); // 새로운 카테고리 저장
            }

            return true;
        } catch (Exception e) {
            log.error("Failed to save prompt: {}", e.getMessage());
            return false;
        }
    }

}