package com.devjck.springboard.controller.reply;

import com.devjck.springboard.dto.reply.ReplySaveRequestDto;
import com.devjck.springboard.dto.reply.ReplyUpdateRequestDto;
import com.devjck.springboard.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply/insert")
    public Long save(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        return replyService.save(replySaveRequestDto);
    }

    @PutMapping("/reply/update/{replyId}")
    public Long update(@PathVariable Long replyId, @RequestBody ReplyUpdateRequestDto replyUpdateRequestDto) {
        return replyService.update(replyId, replyUpdateRequestDto);
    }

}
