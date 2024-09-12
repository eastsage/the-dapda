package com.the_dapda.domain.diary.dto.response;

import com.the_dapda.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryGetResponse {

    private String question;
    private String content;
    private String answer;

    public static DiaryGetResponse diaryToDiaryGetResponse(Diary diary) {
        return new DiaryGetResponse(
                diary.getQuestion(),
                diary.getContent(),
                diary.getAnswer());
    }
}