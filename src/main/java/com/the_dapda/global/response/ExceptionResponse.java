package com.the_dapda.global.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ExceptionResponse implements ResponseInterface {
    private final String exceptionMessage;
    private final String exceptionCode;
}
