package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.response.QuestionResponse;
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

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseForm> getQuestion(@PathVariable("categoryId") Long categoryId) {
        QuestionResponse questionResponse = diaryQueryService.getQuestion(categoryId);

        return questionResponse != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, questionResponse)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL ));
    }
}
