package com.the_dapda.domain.ai.service;

import com.the_dapda.domain.ai.dto.request.ChatRequestDto;
import com.the_dapda.domain.ai.dto.response.ChatResponseDto;

public interface ChatService {

    ChatResponseDto getAnswerAboutQuestion(ChatRequestDto requestDto);

    ChatResponseDto getQuestionAboutCategory(String title, String prompt);
}
