package com.the_dapda.domain.user.service;

import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.global.response.ResponseForm;

public interface MemberService {
    ResponseForm register(UserDto memberDto);
    ResponseForm login(UserDto memberDto); // 로그인 기능 추가
    ResponseForm logout();
}
