package com.the_dapda.domain.user.service;

import com.the_dapda.domain.user.dto.LoginDto;
import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.domain.user.entity.User;

public interface UserService {

    // 회원가입 로직
    User register(UserDto userDto);

    // 로그인 로직, 세션에 사용자 저장
    User login(LoginDto loginDto);

    // 로그아웃 로직, 세션 무효화
    void logout();

    // 사용자 조회
    User findUserById(String userId);
}
