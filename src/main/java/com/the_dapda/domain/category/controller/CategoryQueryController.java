package com.the_dapda.domain.category.controller;

import com.the_dapda.domain.category.dto.request.SavePromptRequestDto;
import com.the_dapda.domain.category.dto.response.CategoryResponse;
import com.the_dapda.domain.category.service.CategoryQueryService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping
    public ResponseEntity<ResponseForm> getCategories() {
        List<CategoryResponse> categoryResponses = categoryQueryService.getCategories();

        return categoryResponses != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, categoryResponses)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL ));
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