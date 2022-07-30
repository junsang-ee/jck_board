package com.devjck.springboard.controller.nestedReply;

import com.devjck.springboard.dto.nestedReply.NestedReplySaveRequestDto;
import com.devjck.springboard.service.nestedReply.NestedReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NestedReplyController {

    private final NestedReplyService nestedReplyService;

    @PostMapping("/api/nested-reply")
    public Long save(@RequestBody NestedReplySaveRequestDto nestedReplySaveRequestDto) {
        return nestedReplyService.save(nestedReplySaveRequestDto);
    }

}
