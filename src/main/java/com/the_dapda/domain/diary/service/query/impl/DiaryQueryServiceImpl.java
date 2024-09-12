package com.the_dapda.domain.diary.service.query.impl;

import com.the_dapda.domain.category.entity.Category;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.QuestionGetResponse;
import com.the_dapda.domain.diary.entity.Diary;
import com.the_dapda.domain.diary.repository.DiaryRepository;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryQueryServiceImpl implements DiaryQueryService {

    private final DiaryRepository diaryRepository;
    private final CategoryRepository categoryRepository;

    public QuestionGetResponse getQuestion(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("no category"));
        log.info("카테고리 정보 {}", category);

        // 카테고리를 통해 질문글 조회 로직 필요

        return new QuestionGetResponse();
    }

    @Override
    public DiaryGetResponse getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new RuntimeException("no diary"));

        return DiaryGetResponse.diaryToDiaryGetResponse(diary);
    }
}