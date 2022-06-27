package com.devjck.springboard.controller.board;

import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import com.devjck.springboard.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        return boardService.save(boardSaveRequestDto);
    }
}
