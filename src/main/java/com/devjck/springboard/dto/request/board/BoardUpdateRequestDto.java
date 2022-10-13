package com.devjck.springboard.dto.request.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;

    private String content;

    private String password;

    private String openRange;

    private int status;

    @Builder
    public BoardUpdateRequestDto(String title, String content,
                                 String password, String openRange, int status) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
        this.status = status;
    }

}
