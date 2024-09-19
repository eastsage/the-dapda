package com.the_dapda.domain.diary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiarySaveResponse {

    private int year;
    private int month;
    private int day;
    private String question;
    private String content;
    private String answer;

    public DiarySaveResponse(String question, String content, String answer) {
        this.question = question;
        this.content = content;
        this.answer = answer;
    }

    public static DiarySaveResponse from(DiaryGetResponse diaryGetResponse) {
        return new DiarySaveResponse(
                diaryGetResponse.getQuestion(),
                diaryGetResponse.getContent(),
                diaryGetResponse.getAnswer()
        );
    }
}
