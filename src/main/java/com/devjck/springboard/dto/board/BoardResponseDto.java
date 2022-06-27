package com.devjck.springboard.dto.board;

import com.devjck.springboard.domain.board.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long boardSeq;

    private int boardWriterId;

    private String boardContents;

    private String boardPassword;

    private String boardOpenRange;

    public BoardResponseDto(Board board) {
        this.boardSeq = board.getBoardSeq();
        this.boardWriterId = board.getBoardWriterId();
        this.boardContents = board.getBoardContents();
        this.boardPassword = board.getBoardPassword();
        this.boardOpenRange = board.getBoardOpenRange();
    }
}
