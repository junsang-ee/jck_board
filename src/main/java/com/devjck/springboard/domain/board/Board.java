package com.devjck.springboard.domain.board;

import com.devjck.springboard.domain.common.BaseEntity;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.user.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity(name = "board")
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "writeUser", nullable = false)
    private User writeUser;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String openRange;

    @Column(nullable = false)
    private int status;

    @JsonIgnore
    @OneToMany(mappedBy = "parentBoard", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> reply = new ArrayList<>();

    @Builder
    public Board(User writeUser, String title, String content, String password, String openRange, int status) {
        this.writeUser = writeUser;
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
        this.status = status;
    }

    public void update(String title, String content,
                  String password, String openRange) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
    }

}
