package com.the_dapda.domain.user.controller;

import com.the_dapda.domain.user.dto.LoginDto;
import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.entity.User;
import com.the_dapda.domain.user.service.UserService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<ResponseForm> register(@RequestBody UserDto userDto) {
        try {
            User user = userService.register(userDto);
            return ResponseEntity.ok(ResponseForm.of(ResponseCode.AUTH_REGISTER_SUCCESS, user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ResponseForm.of(ResponseCode.AUTH_REGISTER_FAIL, e.getMessage()));
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseForm> login(@RequestBody LoginDto loginDto, HttpSession session) {
        try {
            User user = userService.login(loginDto);
            if (user != null) {
                session.setAttribute("user", user);  // 세션에 사용자 객체 저장
                return ResponseEntity.ok(ResponseForm.of(ResponseCode.AUTH_LOGIN_SUCCESS, user));
            } else {
                return ResponseEntity.badRequest().body(ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, "로그인 실패"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, e.getMessage()));
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<ResponseForm> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 세션이 있으면 가져옴
        if (session != null) {
            session.invalidate();  // 세션 무효화
            return ResponseEntity.ok(ResponseForm.of(ResponseCode.AUTH_LOGOUT_SUCCESS, "로그아웃 성공"));
        } else {
            return ResponseEntity.badRequest().body(ResponseForm.of(ResponseCode.AUTH_LOGOUT_FAIL, "로그아웃 실패"));
        }
    }
}
