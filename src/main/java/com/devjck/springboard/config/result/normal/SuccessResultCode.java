package com.devjck.springboard.config.result.normal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessResultCode {

    // TODO: 2022-10-12 add Logic enum Data 
    SUCCESS_MODIFY_USER_INFO(200, "회원의 정보가 정상적으로 수정되었습니다.");

    private final int resultCode;
    private final String resultMessage;

}
