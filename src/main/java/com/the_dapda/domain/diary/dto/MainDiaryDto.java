package com.the_dapda.domain.diary.dto;

import com.the_dapda.domain.diary.entity.Date;
import com.the_dapda.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainDiaryDto {

    private Date date;
    private Long diaryId;
    private String content;
    private String question;
    private String answer;

    public MainDiaryDto(Diary diary) {
        this.date = diary.getDate();
        this.diaryId = diary.getId();
        this.content = diary.getContent();
        this.question = diary.getQuestion();
        this.answer = diary.getAnswer();
    }
}
