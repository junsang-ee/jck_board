package com.devjck.springboard.dto.board;

import com.devjck.springboard.domain.board.Board;
import lombok.Getter;
import lombok.ToString;

@Getter
public class BoardSimpleResponseDto {
    private Long boardId;

    private String userNickName;

    private String content;

    private String passsword;

    private String openRange;

    private int status;

    public BoardSimpleResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.userNickName = board.getWriteUser().getNickName();
        this.content = board.getContent();
        this.passsword = board.getPassword();
        this.openRange = board.getOpenRange();
        this.status = board.getStatus();
    }

    @Override
    public String toString() {
        return "BoardSimpleResponseDto{" +
                "boardId=" + boardId +
                ", userNickName='" + userNickName + '\'' +
                ", content='" + content + '\'' +
                ", passsword='" + passsword + '\'' +
                ", openRange='" + openRange + '\'' +
                ", status=" + status +
                '}';
    }
}
