package com.the_dapda.domain.category.service;

import com.the_dapda.domain.category.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryQueryService {

    List<CategoryResponse> getCategories();
}