package com.devjck.springboard.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "board")
@AllArgsConstructor
@NoArgsConstructor
public class Board{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;

    @Column
    private int boardWriterId;

    @Column
    private String boardContents;

    @Column
    private String boardPassword;

    @Column
    private LocalDateTime boardWriteDate;

    @Column
    private String boardOpenRange;

    @Builder
    public Board(int boardWriterId, String boardContents, String boardPassword, String boardOpenRange) {
        this.boardWriterId = boardWriterId;
        this.boardContents = boardContents;
        this.boardPassword = boardPassword;
        this.boardOpenRange = boardOpenRange;
        this.boardWriteDate = LocalDateTime.now();
    }

}
