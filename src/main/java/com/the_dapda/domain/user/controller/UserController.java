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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지로 이동
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDto", new UserDto()); // UserDto 객체 추가
        return "signup"; // 타임리프 템플릿에서 "signup.html" 파일을 찾음
    }


    // 회원가입
    @PostMapping("/register")
    public String register(UserDto registerDto, Model model) {
        try {
            User user = userService.register(registerDto);
            model.addAttribute("message", ResponseForm.of(ResponseCode.AUTH_REGISTER_SUCCESS, user));
            return "login"; // 회원가입 후 로그인 페이지로 이동
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", ResponseForm.of(ResponseCode.AUTH_REGISTER_FAIL, e.getMessage()));
            return "signup"; // 실패 시 다시 회원가입 페이지로 이동
        }
    }

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login"; // 타임리프 템플릿에서 "login.html" 파일을 찾음
    }

    // 로그인
    @PostMapping("/login")
    public String login(LoginDto loginDto, HttpSession session, Model model) {
        try {
            User user = userService.login(loginDto);
            if (user != null) {
                session.setAttribute("user", user);  // 세션에 사용자 객체 저장
                model.addAttribute("message", ResponseForm.of(ResponseCode.AUTH_LOGIN_SUCCESS, user));
                return "main"; // 로그인 성공 시 홈 페이지로 이동
            } else {
                model.addAttribute("error", ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, "로그인 실패"));
                return "login"; // 로그인 실패 시 다시 로그인 페이지로 이동
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, e.getMessage()));
            return "login"; // 로그인 실패 시 다시 로그인 페이지로 이동
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);  // 세션이 있으면 가져옴
        if (session != null) {
            session.invalidate();  // 세션 무효화
            model.addAttribute("message", ResponseForm.of(ResponseCode.AUTH_LOGOUT_SUCCESS, "로그아웃 성공"));
            return "login"; // 로그아웃 후 로그인 페이지로 이동
        } else {
            model.addAttribute("error", ResponseForm.of(ResponseCode.AUTH_LOGOUT_FAIL, "로그아웃 실패"));
            return "login"; // 세션이 없을 때도 로그인 페이지로 이동
        }
    }
}
