package com.the_dapda.domain.category.controller;

import com.the_dapda.domain.category.dto.request.SavePromptRequestDto;
import com.the_dapda.domain.category.dto.response.CategoryResponse;
import com.the_dapda.domain.category.service.CategoryQueryService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import com.the_dapda.global.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping
    public String getCategories(@RequestParam(value = "year", required = false) Integer year,
                                @RequestParam(value = "month", required = false) Integer month,
                                @RequestParam(value = "day", required = false) Integer day,
                                HttpServletRequest request,
                                Model model) {

        HttpSession session = request.getSession();
        session.setAttribute(String.valueOf(SessionConst.YEAR), year);
        session.setAttribute(String.valueOf(SessionConst.MONTH), month);
        session.setAttribute(String.valueOf(SessionConst.DAY), day);

        List<CategoryResponse> categoryResponses = categoryQueryService.getCategories();

        model.addAttribute("categoryResponses", categoryResponses);

        return "select-question";
    }

    @PatchMapping("/updatePrompt") // 카테고리에 새로운 prompt 저장 및 수정
    public ResponseEntity<ResponseForm> savePrompt(@RequestBody SavePromptRequestDto requestDto) {
        // DTO로부터 title과 prompt 값을 가져옴
        boolean result = categoryQueryService.savePrompt(requestDto.getTitle(), requestDto.getPrompt());

        return result ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL));
    }
}