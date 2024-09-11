package com.the_dapda.domain.example.controller;

import com.the_dapda.domain.example.dto.BannerListResponse;
import com.the_dapda.domain.example.service.MatchingService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.stereotype.Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/matching")
@Tag(name = "Matching API", description = "매칭 API")
public class MatchingController {

    private final MatchingService matchingService;

    @Operation(summary = "배너 매칭 목록 조회", description = "배너에 표시할 멤버 목록을 조회합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "B001 - 배너 목록 조회 성공")
    @ApiResponse(responseCode = "400", description = "B001 - 배너 목록 조회 실패")

    @GetMapping("/banner")
    public ResponseEntity<ResponseForm> getMemberForBanner() {
        List<BannerListResponse> bannerList = matchingService.getBannerList();

        return bannerList != null ?
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_SUCCESS, bannerList)) :
                ResponseEntity.ok(ResponseForm.of(ResponseCode.EXAMPLE_FAIL ));
    }
}