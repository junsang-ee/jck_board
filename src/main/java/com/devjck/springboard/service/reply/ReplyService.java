package com.devjck.springboard.service.reply;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.reply.ReplyRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.reply.ReplySaveRequestDto;
import com.devjck.springboard.dto.reply.ReplyUpdateRequestDto;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public Long save(ReplySaveRequestDto replySaveRequestDto) {
        return replyRepository.save(replySaveRequestDto.toEntity()).getReplyId();
    }

    @Transactional
    public Long update(Long replyId, ReplyUpdateRequestDto replyUpdateRequestDto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("don't exists reply")
        );
        reply.update(replyUpdateRequestDto.getContent());

        return replyId;
    }


}
