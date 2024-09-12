package com.the_dapda.domain.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePromptRequestDto {
    private String title;
    private String prompt;
}
