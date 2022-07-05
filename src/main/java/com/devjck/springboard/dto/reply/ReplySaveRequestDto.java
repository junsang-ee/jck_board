package com.devjck.springboard.dto.reply;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplySaveRequestDto {

    private Board parentBoard;
    private String content;
    private User replyUser;

    @Builder
    public ReplySaveRequestDto(Board parentBoard, String content, User replyUser) {
        this.parentBoard = parentBoard;
        this.content = content;
        this.replyUser = replyUser;
    }

    public Reply toEntity() {
        return Reply.builder()
                .parentBoard(parentBoard)
                .content(content)
                .replyUser(replyUser)
                .build();
    }

}
