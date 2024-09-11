package com.the_dapda.domain.example.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class BannerListResponse {
    private final String senderNickname;
    private final String senderMbti;
    private final String senderGender;
    private final String receiverNickname;
    private final String receiverMbti;
    private final String receiverGender;
}
