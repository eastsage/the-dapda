package com.the_dapda.domain.category.service.impl;

import com.the_dapda.domain.category.dto.response.CategoryResponse;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.category.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();
    }
}