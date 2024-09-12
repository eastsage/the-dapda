package com.the_dapda.domain.category.dto.response;

import com.the_dapda.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long categoryId;
    private String title;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.title = category.getTitle();
    }
}