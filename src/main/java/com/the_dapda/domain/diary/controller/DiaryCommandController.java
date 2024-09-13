package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.request.DiarySaveRequest;
import com.the_dapda.domain.diary.dto.response.DiaryDeleteResponse;
import com.the_dapda.domain.diary.dto.response.DiarySaveResponse;
import com.the_dapda.domain.diary.entity.Date;
import com.the_dapda.domain.diary.service.command.DiaryCommandService;
import com.the_dapda.domain.user.entity.User;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import com.the_dapda.global.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryCommandController {

    private final DiaryCommandService diaryCommandService;

    // 일기 저장
    @PostMapping
    public ResponseEntity<ResponseForm> saveDiary(
            @RequestBody DiarySaveRequest saveRequest,
            HttpServletRequest request) {

        HttpSession session = request.getSession();
        int year = (Integer) session.getAttribute(String.valueOf(SessionConst.YEAR));
        int month = (Integer) session.getAttribute(String.valueOf(SessionConst.MONTH));
        int day = (Integer) session.getAttribute(String.valueOf(SessionConst.DAY));
        log.info("year {}", year);
        log.info("month {}", month);
        log.info("day {}", day);

        User user = (User) session.getAttribute("user");
        log.info("user {}", user);

        saveRequest.setDate(new Date(year, month, day));
        DiarySaveResponse diarySaveResponse = diaryCommandService.saveDiary(saveRequest, user);

        return diarySaveResponse != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, diarySaveResponse)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }

    // 일기 삭제
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<ResponseForm> deleteDiary(@PathVariable("diaryId") Long diaryId) {
        DiaryDeleteResponse diaryDeleteResponse = diaryCommandService.deleteDiary(diaryId);

        return diaryDeleteResponse != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, diaryDeleteResponse)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }
}