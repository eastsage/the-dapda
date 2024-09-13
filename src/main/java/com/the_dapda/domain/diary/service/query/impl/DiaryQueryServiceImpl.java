package com.the_dapda.domain.diary.service.query.impl;

import com.the_dapda.domain.ai.service.ChatService;
import com.the_dapda.domain.category.entity.Category;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.diary.dto.DiaryDto;
import com.the_dapda.domain.diary.dto.MainDiaryDto;
import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.QuestionGetResponse;
import com.the_dapda.domain.diary.entity.Diary;
import com.the_dapda.domain.diary.repository.DiaryRepository;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryQueryServiceImpl implements DiaryQueryService {

    private final DiaryRepository diaryRepository;
    private final CategoryRepository categoryRepository;
    private final ChatService chatService;

    public QuestionGetResponse getQuestion(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("no category"));
        log.info("카테고리 정보 {}", category);

        String title = category.getTitle();
        String prompt = category.getPrompt();

        // 카테고리를 통해 질문글 생성 로직 필요
        String message = chatService.getQuestionAboutCategory(title, prompt).getMessage();
        return new QuestionGetResponse(message);
    }

    @Override
    public DiaryGetResponse getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new RuntimeException("no diary"));

        return DiaryGetResponse.diaryToDiaryGetResponse(diary);
    }

    @Override
    public Page<DiaryDto> getDiaries(Pageable pageable) {
        Page<Diary> diaries = diaryRepository.findAll(pageable);
        return diaries.map(DiaryDto::new);
    }

    @Override
    public List<MainDiaryDto> getMainDiaries(int year, int month, int userId) {
        return diaryRepository.getMainDiaries(year, month, userId)
                .stream()
                .map(MainDiaryDto::new)
                .toList();
    }
}