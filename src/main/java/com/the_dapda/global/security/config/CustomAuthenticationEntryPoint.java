package com.the_dapda.global.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 인증 실패시 결과를 처리해주는 로직을 가지고 있는 클래스
 * 예제 13.21, 예제 13.32
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ResponseForm errorResponse = ResponseForm.of(ResponseCode.AUTH_LOGIN_FAIL, "Unauthorized access - please log in");
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}