package com.the_dapda.domain.ai.dto.response;

import lombok.Data;

@Data
public class ChatResponseDto {
    private String message;

    public ChatResponseDto(String message) {
        this.message = message;
    }
}
