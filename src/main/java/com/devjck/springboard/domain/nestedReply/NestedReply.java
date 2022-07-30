package com.devjck.springboard.domain.nestedReply;

import com.devjck.springboard.domain.common.BaseEntity;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Data
@Entity(name = "nested_reply")
@AllArgsConstructor
@NoArgsConstructor
public class NestedReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nestedReplyId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_reply", nullable = false)
    private Reply parentReply;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "nested_reply_writer", nullable = false)
    private User nestedReplyWriter;

    @Builder
    public NestedReply(Reply parentReply, String content, User nestedReplyWriter) {
        this.parentReply = parentReply;
        this.content = content;
        this.nestedReplyWriter = nestedReplyWriter;
    }

}
