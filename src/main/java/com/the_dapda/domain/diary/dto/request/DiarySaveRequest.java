package com.the_dapda.domain.diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiarySaveRequest {
    private String tfMode;
    private String content;
    private String question;
    private Long categoryId;
}