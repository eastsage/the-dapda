package com.the_dapda.domain.ai.dto.request;

import lombok.Data;

@Data
public class ChatRequestDto {
    private String question; // 요청사항
    private String content; // 요청사항

    public ChatRequestDto(String question, String content) {
        this.question = question;
        this.content = content;
    }
}
