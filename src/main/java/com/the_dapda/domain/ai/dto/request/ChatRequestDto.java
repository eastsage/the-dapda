package com.the_dapda.domain.ai.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRequestDto {
    private String question; // 요청사항
    private String content; // 요청사항
    private String tfMode; // 요청사항
}
