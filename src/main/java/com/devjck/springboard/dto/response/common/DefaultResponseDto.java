package com.devjck.springboard.dto.response.common;

import com.devjck.springboard.config.result.normal.SuccessResultCode;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class DefaultResponseDto {
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final int resultCode;
    private final String resultMessage;

    public static DefaultResponseDto toResponseEntity(SuccessResultCode successResultCode) {
        return DefaultResponseDto.builder()
                .resultCode(successResultCode.getResultCode())
                .resultMessage(successResultCode.getResultMessage())
                .build();
    }

}
