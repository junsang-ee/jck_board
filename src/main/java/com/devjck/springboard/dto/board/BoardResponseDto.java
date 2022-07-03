package com.devjck.springboard.dto.board;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.user.User;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long boardId;

    private User writeUser;

    private String content;

    private String passsword;

    private String openRange;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.writeUser = board.getWriteUser();
        this.content = board.getContent();
        this.passsword = board.getPassword();
        this.openRange = board.getOpenRange();
    }
}
