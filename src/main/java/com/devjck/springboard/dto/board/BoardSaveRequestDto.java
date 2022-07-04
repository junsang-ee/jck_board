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

    private String title;

    private String content;

    private String password;

    private String openRange;


    @Builder
    public BoardSaveRequestDto(User writeUser, String title, String content,
                               String password, String openRange) {
        this.writeUser = writeUser;
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
    }

    public Board toEntity() {
        return Board.builder()
                .writeUser(writeUser)
                .title(title)
                .content(content)
                .password(password)
                .openRange(openRange)
                .build();
    }
}
