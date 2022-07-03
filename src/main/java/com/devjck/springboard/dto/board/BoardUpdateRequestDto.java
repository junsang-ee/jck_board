package com.devjck.springboard.dto.board;

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

    @Builder
    public BoardUpdateRequestDto(String title, String content,
                                 String password, String openRange) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
    }
}
