package com.the_dapda.domain.diary.service.query;

import com.the_dapda.domain.diary.dto.DiaryDto;
import com.the_dapda.domain.diary.dto.MainDiaryDto;
import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.QuestionGetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryQueryService {

    QuestionGetResponse getQuestion(Long categoryId);

    DiaryGetResponse getDiary(Long diaryId);

    Page<DiaryDto> getDiaries(Pageable pageable);

    List<MainDiaryDto> getMainDiaries(int year, int month, int userId);
}
