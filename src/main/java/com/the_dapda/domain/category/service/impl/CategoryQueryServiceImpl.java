package com.the_dapda.domain.category.service.impl;

import com.the_dapda.domain.category.dto.response.CategoryResponse;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.category.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    public boolean savePrompt(Long categoryId, String prompt) {
        try {
            categoryRepository.updatePromptById(categoryId, prompt);
            return true;
        } catch (Exception e) {
            log.error("Failed to save propmt : {}", e.getMessage());
            return false;
        }
    }
}