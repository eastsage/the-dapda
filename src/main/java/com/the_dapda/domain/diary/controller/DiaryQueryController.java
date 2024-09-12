package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.QuestionGetResponse;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryQueryController {

    private final DiaryQueryService diaryQueryService;

    @GetMapping("/question/category/{categoryId}")
    public ResponseEntity<ResponseForm> getQuestion(@PathVariable("categoryId") Long categoryId) {
        QuestionGetResponse questionGetResponse = diaryQueryService.getQuestion(categoryId);

        return questionGetResponse != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, questionGetResponse)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<ResponseForm> getDiary(@PathVariable("diaryId") Long diaryId) {
        DiaryGetResponse diaryGetResponse = diaryQueryService.getDiary(diaryId);

        return diaryGetResponse != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, diaryGetResponse)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }
}
