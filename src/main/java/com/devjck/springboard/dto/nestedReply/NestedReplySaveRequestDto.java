package com.devjck.springboard.dto.nestedReply;

import com.devjck.springboard.domain.nestedReply.NestedReply;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NestedReplySaveRequestDto {

    private Reply parentReply;
    private String content;
    private User nestedReplyWriter;

    @Builder
    public NestedReplySaveRequestDto(Reply parentReply, String content, User nestedReplyWriter) {
        this.parentReply = parentReply;
        this.content = content;
        this.nestedReplyWriter = nestedReplyWriter;
    }

    public NestedReply toEntity() {
        return NestedReply.builder()
                .parentReply(parentReply)
                .content(content)
                .nestedReplyWriter(nestedReplyWriter)
                .build();
    }

}
