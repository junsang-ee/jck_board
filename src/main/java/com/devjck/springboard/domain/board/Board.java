package com.devjck.springboard.domain.board;

import com.devjck.springboard.domain.common.BaseEntity;
import com.devjck.springboard.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "board")
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writeUser", nullable = false)
    private User writeUser;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String openRange;

    @Builder
    public Board(User writeUser, String title, String content, String password, String openRange) {
        this.writeUser = writeUser;
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
    }

    public void update(String title, String content,
                  String password, String openRange) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.openRange = openRange;
    }

}
