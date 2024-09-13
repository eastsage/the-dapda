package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.DiaryDto;
import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.QuestionGetResponse;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import com.the_dapda.global.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryQueryController {

    private final DiaryQueryService diaryQueryService;

    @GetMapping("/question")
    public ResponseEntity<ResponseForm> getQuestion(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "day", required = false) Integer day,
            @RequestParam(value = "categoryId") Long categoryId,
            HttpServletRequest request) {

        QuestionGetResponse questionGetResponse = diaryQueryService.getQuestion(categoryId);

        HttpSession session = request.getSession();
        session.setAttribute(String.valueOf(SessionConst.YEAR), year);
        session.setAttribute(String.valueOf(SessionConst.MONTH), month);
        session.setAttribute(String.valueOf(SessionConst.DAY), day);

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

    @GetMapping
    public ResponseEntity<ResponseForm> getDiaries(Pageable pageable) {
        Page<DiaryDto> diaryDtos = diaryQueryService.getDiaries(pageable);

        return diaryDtos != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, diaryDtos)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }
}
