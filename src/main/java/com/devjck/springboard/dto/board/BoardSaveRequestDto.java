package com.devjck.springboard.dto.board;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private User writeUser;

    private String content;

    private String password;

    private String openRange;


    @Builder
    public BoardSaveRequestDto(User writeUser, String content,
                               String password, String openRange) {
        this.writeUser = writeUser;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
    }

    public Board toEntity() {
        return Board.builder()
                .writeUser(writeUser)
                .content(content)
                .password(password)
                .openRange(openRange)
                .build();
    }
}
