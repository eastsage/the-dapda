package com.example.the_dapda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.service.MemberService;
import com.the_dapda.global.response.ResponseCode;
import com.the_dapda.global.response.ResponseForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
public class MemberControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 테스트 - 성공")
    void testRegisterSuccess() throws Exception {
        // Given
        UserDto memberDto = new UserDto();
        memberDto.setId("testUser");
        memberDto.setPassword("testPassword");
        memberDto.setNickname("testNickname");

        given(memberService.register(any(UserDto.class)))
                .willReturn(ResponseForm.of(ResponseCode.AUTH_REGISTER_SUCCESS, "회원가입 성공"));

        // When & Then
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseCode.AUTH_REGISTER_SUCCESS.getCode()))
                .andExpect(jsonPath("$.message").value("회원가입 성공"));
    }

    @Test
    @DisplayName("로그인 테스트 - 성공")
    void testLoginSuccess() throws Exception {
        // Given
        UserDto memberDto = new UserDto();
        memberDto.setId("testUser");
        memberDto.setPassword("testPassword");

        String token = "dummyJwtToken";
        given(memberService.login(any(UserDto.class)))
                .willReturn(ResponseForm.of(ResponseCode.AUTH_LOGIN_SUCCESS, token));

        // When & Then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseCode.AUTH_LOGIN_SUCCESS.getCode()))
                .andExpect(jsonPath("$.data").value(token));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("로그아웃 테스트 - 성공")
    void testLogoutSuccess() throws Exception {
        // Given
        given(memberService.logout())
                .willReturn(ResponseForm.of(ResponseCode.AUTH_LOGOUT_SUCCESS, "로그아웃 성공"));

        // When & Then
        mockMvc.perform(post("/api/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResponseCode.AUTH_LOGOUT_SUCCESS.getCode()))
                .andExpect(jsonPath("$.message").value("로그아웃 성공"));
    }
}
