package com.devjck.springboard.domain.reply;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name="parentBoard", nullable = false)
    private Board parentBoard;

    @Column
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "replyUser", nullable = false)
    private User replyUser;

    @Builder
    public Reply(Board parentBoard, String content, User replyUser) {
        this.parentBoard = parentBoard;
        this.content = content;
        this.replyUser = replyUser;
    }

    public void update(String content) {
        this.content = content;
    }
}
