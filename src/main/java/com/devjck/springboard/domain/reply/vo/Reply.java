package com.devjck.springboard.domain.reply.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    private int replySeq;

    @Column
    private int replyRefBoardSeq;

    @Column
    private String replyContents;

    @Column
    private String replyWriteDate;

    @Column
    private int replyUserId;
}
