package com.devjck.springboard.domain.nestedReply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "nested_reply")
@AllArgsConstructor
@NoArgsConstructor
public class NestedReply {
    @Id
    private int nestedReplySeq;

    @Column
    private int nestedRefReplySeq;

    @Column
    private String nestedReplyContents;

    @Column
    private int nestedReplyWriteUserId;

    @Column
    private String nestedReplyWriteDate;
}
