package com.the_dapda.domain.ai.controller;

import com.the_dapda.domain.ai.dto.request.ChatRequestDto;
import com.the_dapda.domain.ai.dto.response.ChatResponseDto;
import com.the_dapda.domain.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat") // ai 대화 테스트 코드
    public ChatResponseDto chat(@RequestBody ChatRequestDto requestDto) {
        return chatService.getAnswerAboutQuestion(requestDto);
    }

}
