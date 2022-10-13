package com.devjck.springboard.controller.reply;

import com.devjck.springboard.dto.request.reply.ReplySaveRequestDto;
import com.devjck.springboard.dto.request.reply.ReplyUpdateRequestDto;
import com.devjck.springboard.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/reply")
    public Long save(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        return replyService.save(replySaveRequestDto);
    }

    @PutMapping("/api/reply/{replyId}")
    public Long update(@PathVariable Long replyId, @RequestBody ReplyUpdateRequestDto replyUpdateRequestDto) {
        return replyService.update(replyId, replyUpdateRequestDto);
    }

}
