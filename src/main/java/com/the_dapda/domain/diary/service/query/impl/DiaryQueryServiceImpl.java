package com.the_dapda.domain.diary.service.query.impl;

import com.the_dapda.domain.category.entity.Category;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.diary.dto.response.QuestionResponse;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryQueryServiceImpl implements DiaryQueryService {

    private final CategoryRepository categoryRepository;

    public QuestionResponse getQuestion(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("no category"));

        // 카테고리를 통해 질문글 조회 로직 필요

        return new QuestionResponse();
    }
}