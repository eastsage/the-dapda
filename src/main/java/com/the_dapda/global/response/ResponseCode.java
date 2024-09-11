package com.the_dapda.global.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * Response Convention
 * - 도메인 별로 나누어 관리
 * - [동사_목적어_SUCCESS] 형태로 생성
 * - 코드는 도메인명 앞에서부터 1~2글자로 사용
 * - 메시지는 "~니다."로 마무리
 */

/*
 * Business Response Code
 * A  : Authentication or Authorization
 * U  : User
 * S  : Sign-up
 */


@Getter
@AllArgsConstructor
public enum ResponseCode {

    // example
    EXAMPLE_SUCCESS(200, "E001", "응답에 성공했습니다.."),
    EXAMPLE_FAIL(400, "E001", "응답에 실패했습니다.."),
    ;

    // field
    private final int status;
    private final String code;
    private final String message;
}
