package com.devjck.springboard.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "board")
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    private int boardSeq;

    @Column
    private int boardWriterId;

    @Column
    private String boardContents;

    @Column
    private String boardPassword;

    @Column
    private String boardWriteDate;

    @Column
    private String boardOpenRange;

//    @Builder
//    public Board(int boardWriterId, String boardContents, String boardPassword, String boardWriteDate, String boardOpenRange) {
//        this.boardWriterId = boardWriterId;
//        this.boardContents = boardContents;
//        this.boardPassword = boardPassword;
//        this.boardWriteDate = boardWriteDate;
//        this.boardOpenRange = boardOpenRange;
//    }
//
//    public Board toEntity() {
//        return Board.builder()
//                .boardWriterId(boardWriterId)
//                .boardContents(boardContents)
//                .boardPassword(boardPassword)
//                .boardWriteDate(boardWriteDate)
//                .boardOpenRange(boardOpenRange)
//                .build();
//    }
}
