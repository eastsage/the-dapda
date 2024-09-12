package com.the_dapda.domain.diary.service.command.impl;

import com.the_dapda.domain.category.entity.Category;
import com.the_dapda.domain.category.repository.CategoryRepository;
import com.the_dapda.domain.diary.dto.request.DiarySaveRequest;
import com.the_dapda.domain.diary.dto.response.DiaryDeleteResponse;
import com.the_dapda.domain.diary.dto.response.DiarySaveResponse;
import com.the_dapda.domain.diary.entity.Diary;
import com.the_dapda.domain.diary.repository.DiaryRepository;
import com.the_dapda.domain.diary.service.command.DiaryCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DiaryCommandServiceImpl implements DiaryCommandService {

    private final DiaryRepository diaryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public DiarySaveResponse saveDiary(DiarySaveRequest saveRequest) {
        Category category = categoryRepository.findById(saveRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("no category"));
        log.info("{}", category);

        // AI 로부터 응답 받아오는 로직 필요

        Diary diary = new Diary(saveRequest.getContent(), saveRequest.getQuestion(), null);
        Diary savedDiary = diaryRepository.save(diary);
        return new DiarySaveResponse(savedDiary.getId());
    }

    @Override
    public DiaryDeleteResponse deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new RuntimeException("no diary"));

        diaryRepository.delete(diary);
        return new DiaryDeleteResponse("success");
    }
}