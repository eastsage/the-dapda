package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.DiaryDto;
import com.the_dapda.domain.diary.dto.MainDiaryDto;
import com.the_dapda.domain.diary.dto.response.DiaryGetResponse;
import com.the_dapda.domain.diary.dto.response.QuestionGetResponse;
import com.the_dapda.domain.diary.service.query.DiaryQueryService;
import com.the_dapda.domain.user.entity.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryQueryController {

    private final DiaryQueryService diaryQueryService;

//    @GetMapping("/question/category")
//    public String questionCategory(@RequestParam("year") int year,
//                                   @RequestParam("month") int month,
//                                   @RequestParam("day") int day,
//                                   Model model) {
//        model.addAttribute("year", year);
//        model.addAttribute("month", month);
//        model.addAttribute("day", day);
//        return "select-question";
//    }

    @GetMapping("/question")
    public String getQuestion(
            Model model,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "day", required = false) Integer day,
            @RequestParam(value = "categoryId") Long categoryId,
            HttpServletRequest request) {

        QuestionGetResponse questionGetResponse = diaryQueryService.getQuestion(categoryId);
        model.addAttribute("question", questionGetResponse.getQuestion());

        HttpSession session = request.getSession();
        session.setAttribute(String.valueOf(SessionConst.YEAR), year);
        session.setAttribute(String.valueOf(SessionConst.MONTH), month);
        session.setAttribute(String.valueOf(SessionConst.DAY), day);

        return "view-question";
    }

    @GetMapping("/{diaryId}")
    public String getDiary(
            Model model,
            @PathVariable("diaryId") Long diaryId) {
        DiaryGetResponse diaryGetResponse = diaryQueryService.getDiary(diaryId);
        model.addAttribute("question", diaryGetResponse.getQuestion());
        model.addAttribute("content", diaryGetResponse.getContent());
        model.addAttribute("answer", diaryGetResponse.getAnswer());

        return "view-diary";
    }

    @GetMapping
    public ResponseEntity<ResponseForm> getDiaries(Pageable pageable) {
        Page<DiaryDto> diaryDtos = diaryQueryService.getDiaries(pageable);

        return diaryDtos != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, diaryDtos)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }

    @GetMapping("/main")
    public String mainPage(Model model,
                           @RequestParam(value = "year", required = false) Integer year,
                           @RequestParam(value = "month", required = false) Integer month,
                           HttpServletRequest request, @AuthenticationPrincipal User securityUser) {

        // 현재 연도와 월 기본값 설정 (파라미터가 없으면 현재 날짜 사용)
        LocalDate now = LocalDate.now();
        int currentYear = (year != null) ? year : now.getYear();
        int currentMonth = (month != null) ? month : now.getMonthValue();
        // 월 이름
        String[] monthNames = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
        String currentMonthName = monthNames[currentMonth - 1];
        // 해당 월의 날짜 수 계산
        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
        int daysInMonth = yearMonth.lengthOfMonth();
        // 오늘 날짜 계산 (해당 월이 현재 월이 아닌 경우 today는 표시하지 않음)
        int today = (currentYear == now.getYear() && currentMonth == now.getMonthValue()) ? now.getDayOfMonth() : -1;
        // 1일이 시작하는 요일을 계산하여 빈칸 리스트 생성
        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        DayOfWeek firstDayWeek = firstDayOfMonth.getDayOfWeek();
        int emptyDaysCount = (firstDayWeek.getValue() - 1); // 해당요일부터 시작
        List<Integer> emptyDays = new ArrayList<>();
        for (int i = 0; i < emptyDaysCount; i++) {
            emptyDays.add(i);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        log.info("user {}", user);

        List<MainDiaryDto> diaryDtos = diaryQueryService.getMainDiaries(currentYear, currentMonth, user.getUserId());
        // 해당 월의 날짜 리스트 생성
        List<Integer> days = new ArrayList<>();
        for (MainDiaryDto diaryDto : diaryDtos) {
            days.add(diaryDto.getDate().getDay());
        }
        log.info("days {}", days);
        log.info("empty days {}", emptyDays);
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("currentMonthName", currentMonthName);
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("daysInMonth", daysInMonth);
        model.addAttribute("today", today);
        model.addAttribute("emptyDays", emptyDays);
        model.addAttribute("days", days);
        return "main"; // main.html로 반환
    }
}
