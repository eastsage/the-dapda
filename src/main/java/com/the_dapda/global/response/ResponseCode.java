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

    // 회원가입 관련 응답
    AUTH_REGISTER_SUCCESS(200, "A001", "회원가입에 성공했습니다."),
    AUTH_REGISTER_FAIL(400, "A002", "회원가입에 실패했습니다."),
    AUTH_REGISTER_DUPLICATE_ID(400, "A003", "이미 사용 중인 ID입니다."),
    AUTH_REGISTER_DUPLICATE_NICKNAME(400, "A004", "이미 사용 중인 닉네임입니다."),

    AUTH_LOGIN_SUCCESS(200, "A005", "로그인에 성공했습니다."),
    AUTH_LOGIN_FAIL(400, "A006", "로그인에 실패했습니다."),
    AUTH_LOGOUT_SUCCESS(200, "A006", "로그아웃에 성공했습니다.");

    ;

    // field
    private final int status;
    private final String code;
    private final String message;
}
