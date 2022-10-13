package com.devjck.springboard.dto.response.board;

import com.devjck.springboard.domain.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSimpleResponseDto {
    private Long boardId;

    private String userNickName;

    private String content;

    private String passsword;

    private String openRange;

    private int status;

    private int replyCount;

    public BoardSimpleResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.userNickName = board.getWriteUser().getNickName();
        this.content = board.getContent();
        this.passsword = board.getPassword();
        this.openRange = board.getOpenRange();
        this.status = board.getStatus();
        this.replyCount = board.getReply().size();
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
                ", replyCount=" + replyCount +
                '}';
    }

}
