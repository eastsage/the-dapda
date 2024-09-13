package com.the_dapda.domain.diary.dto.request;

import com.the_dapda.domain.diary.entity.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiarySaveRequest {

    private Date date;
    private String tfMode;
    private String content;
    private String question;
    private Long categoryId;
}