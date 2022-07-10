package com.devjck.springboard.controller.board;

import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import com.devjck.springboard.dto.board.BoardUpdateRequestDto;
import com.devjck.springboard.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/insert")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        return boardService.save(boardSaveRequestDto);
    }

    @PutMapping("/board/update/{boardSeq}")
    public Long update(@PathVariable Long boardSeq, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.update(boardSeq, boardUpdateRequestDto);
    }

}

