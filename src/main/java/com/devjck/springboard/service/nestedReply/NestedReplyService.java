package com.devjck.springboard.service.nestedReply;

import com.devjck.springboard.domain.nestedReply.NestedReplyRepository;
import com.devjck.springboard.dto.nestedReply.NestedReplySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class NestedReplyService {

    private final NestedReplyRepository nestedReplyRepository;

    @Transactional
    public Long save(NestedReplySaveRequestDto nestedReplySaveRequestDto) {
        return nestedReplyRepository.save(nestedReplySaveRequestDto.toEntity()).getNestedReplyId();
    }

}
