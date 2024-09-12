package com.the_dapda.domain.diary.dto;

import com.the_dapda.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {

    private Long diaryId;
    private String content;
    private String question;
    private String answer;

    public DiaryDto(Diary diary) {
        this.diaryId = diary.getId();
        this.content = diary.getContent();
        this.question = diary.getQuestion();
        this.answer = diary.getAnswer();
    }
}
