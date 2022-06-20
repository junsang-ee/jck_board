package com.devjck.springboard.domain.board.vo;

import lombok.AllArgsConstructor;
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

}
