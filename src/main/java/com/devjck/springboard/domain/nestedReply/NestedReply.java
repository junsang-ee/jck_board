package com.devjck.springboard.domain.nestedReply;

import com.devjck.springboard.domain.common.BaseEntity;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "nested_reply")
@AllArgsConstructor
@NoArgsConstructor
public class NestedReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nestedReplyId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_reply", nullable = false)
    private Reply parentReply;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "nested_reply_writer", nullable = false)
    private User nestedReplyWriter;

}
