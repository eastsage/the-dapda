package com.the_dapda.domain.user.service;

import com.the_dapda.domain.user.dto.LoginDto;
import com.the_dapda.domain.user.dto.UserDto;
import com.the_dapda.global.response.ResponseForm;

public interface UserService {
    public ResponseForm register(UserDto userDto);
    public ResponseForm login(LoginDto login);
    public ResponseForm logout();
}
