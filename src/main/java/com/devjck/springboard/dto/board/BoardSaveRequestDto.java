package com.devjck.springboard.dto.board;

import com.devjck.springboard.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private int boardWriterId;

    private String boardContents;

    private String boardPassword;

    private String boardOpenRange;


    @Builder
    public BoardSaveRequestDto(int boardWriterId, String boardContents,
                               String boardPassword, String boardOpenRange) {
        this.boardWriterId = boardWriterId;
        this.boardContents = boardContents;
        this.boardPassword = boardPassword;
        this.boardOpenRange = boardOpenRange;
    }

    public Board toEntity() {
        return Board.builder()
                .boardWriterId(boardWriterId)
                .boardContents(boardContents)
                .boardPassword(boardPassword)
                .boardOpenRange(boardOpenRange)
                .build();
    }
}
