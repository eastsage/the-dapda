package com.the_dapda.domain.diary.controller;

import com.the_dapda.domain.diary.dto.request.DiarySaveRequest;
import com.the_dapda.domain.diary.dto.response.DiarySaveResponse;
import com.the_dapda.domain.diary.service.command.DiaryCommandService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryCommandController {

    private final DiaryCommandService diaryCommandService;

    @PostMapping
    public ResponseEntity<ResponseForm> saveDiary(@RequestBody DiarySaveRequest saveRequest) {
        DiarySaveResponse diarySaveResponse = diaryCommandService.saveDiary(saveRequest);

        return diarySaveResponse != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, diarySaveResponse)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL ));
    }
}