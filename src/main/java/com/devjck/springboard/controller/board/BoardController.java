package com.devjck.springboard.controller.board;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import com.devjck.springboard.dto.board.BoardSimpleResponseDto;
import com.devjck.springboard.dto.board.BoardUpdateRequestDto;
import com.devjck.springboard.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto, HttpServletRequest request) {
        return boardService.save(boardSaveRequestDto, request);
    }

    @PutMapping("/board/update/{boardSeq}")
    public Long update(@PathVariable Long boardSeq, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.update(boardSeq, boardUpdateRequestDto);
    }

    @GetMapping("/api/board/searchAndGetByBoardTitle")
    public ResponseEntity<?> searchAndGetByBoardTitle(@RequestParam("title") String title) {
        return new ResponseEntity<>(boardService.searchAndGetByBoardTitle(title), HttpStatus.OK);
    }

    @GetMapping("/api/board/searchAndGetBoardByUserNickName")
    public ResponseEntity<?> searchAndGetBoardByUserNickName(@RequestParam("nickName") String nickName) {
        List<Board> boards = boardService.searchAndGetBoardByUserNickName(nickName);
        if (boards == null || boards.size() == 0) {
            System.out.println("noContent : " + HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BoardSimpleResponseDto> boardResponseDtos  = new ArrayList<>();
        boards.stream().forEach(board -> {
            boardResponseDtos.add(new BoardSimpleResponseDto(board));
        });
        for (int i = 0; i < boards.size(); i++) {
            System.out.println("boardResponseDtos : " + boardResponseDtos.get(i));
        }
        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);
    }

}


