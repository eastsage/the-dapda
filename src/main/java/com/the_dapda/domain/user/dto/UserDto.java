package com.the_dapda.domain.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class UserDto {

    private String id;
    private String password;
    private String nickname;
    private LocalDateTime createdDate;
}
