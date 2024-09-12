package com.the_dapda.domain.diary.service.query;

import com.the_dapda.domain.diary.dto.response.QuestionResponse;

public interface DiaryQueryService {

    QuestionResponse getQuestion(Long categoryId);
}
