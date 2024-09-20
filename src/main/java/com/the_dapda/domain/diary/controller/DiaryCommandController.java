package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.request.DiarySaveRequest;
import com.the_dapda.domain.diary.dto.response.DiaryDeleteResponse;
import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.DiaryResponse;
import com.the_dapda.domain.diary.entity.Date;
import com.the_dapda.domain.diary.service.command.DiaryCommandService;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import com.the_dapda.domain.user.entity.User;
import com.the_dapda.global.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryCommandController {

    private final DiaryCommandService diaryCommandService;
    private final DiaryQueryService diaryQueryService;

    // 일기 저장
    @PostMapping
    public String saveDiary(
            DiarySaveRequest saveRequest,
            HttpServletRequest request,
            Model model,
            @AuthenticationPrincipal User user) {

        log.info("diary save request: {}", saveRequest);

        HttpSession session = request.getSession();
        int year = (Integer) session.getAttribute(String.valueOf(SessionConst.YEAR));
        int month = (Integer) session.getAttribute(String.valueOf(SessionConst.MONTH));
        int day = (Integer) session.getAttribute(String.valueOf(SessionConst.DAY));
        Long categoryId = (Long) session.getAttribute("categoryId");
        log.info("year {}", year);
        log.info("month {}", month);
        log.info("day {}", day);
        log.info("user {}", user);

        saveRequest.setDate(new Date(year, month, day));
        saveRequest.setCategoryId(categoryId);
        Long diaryId = diaryCommandService.saveDiary(saveRequest, user);
        DiaryGetResponse diaryGetResponse = diaryQueryService.getDiary(diaryId);

        DiaryResponse diaryResponse = DiaryResponse.from(diaryGetResponse);
        diaryResponse.setYear(year);
        diaryResponse.setMonth(month);
        diaryResponse.setDay(day);
        diaryResponse.setDiaryId(diaryId);

        model.addAttribute("diaryResponse", diaryResponse);
        return "view-diary";
    }

    // 일기 삭제
    @DeleteMapping("/{diaryId}")
    public String deleteDiary(@PathVariable("diaryId") Long diaryId) {
        DiaryDeleteResponse diaryDeleteResponse = diaryCommandService.deleteDiary(diaryId);
        if (diaryDeleteResponse.getResult().equals("success")) {
            return "redirect:/diaries/main";
        }
        return "view-diary";
    }
}