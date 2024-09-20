package com.the_dapda.domain.diary.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryResponse {

    private Long diaryId;
    private int year;
    private int month;
    private int day;
    private String question;
    private String content;
    private String answer;

    public DiaryResponse(String question, String content, String answer) {
        this.question = question;
        this.content = content;
        this.answer = answer;
    }

    public static DiaryResponse from(DiaryGetResponse diaryGetResponse) {
        return new DiaryResponse(
                diaryGetResponse.getQuestion(),
                diaryGetResponse.getContent(),
                diaryGetResponse.getAnswer()
        );
    }
}
