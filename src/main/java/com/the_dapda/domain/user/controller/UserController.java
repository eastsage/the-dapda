package com.the_dapda.domain.user.controller;

import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.service.MemberService;
import com.the_dapda.global.response.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<ResponseForm> register(@RequestBody UserDto memberDto) {
        ResponseForm response = memberService.register(memberDto);
        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseForm> login(@RequestBody UserDto memberDto) {
        ResponseForm response = memberService.login(memberDto);
        return ResponseEntity.ok(response);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<ResponseForm> logout() {
        ResponseForm response = memberService.logout();
        return ResponseEntity.ok(response);
    }
}
