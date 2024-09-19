package com.the_dapda.domain.diary.dto.request;

import com.the_dapda.domain.diary.entity.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiarySaveRequest {

    private Date date;
    private String tfMode;
    private String content;
    private String question;
    private Long categoryId;
}