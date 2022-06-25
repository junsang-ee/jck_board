package com.devjck.springboard;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Board board = new Board();
        board.setBoardSeq(1);
        board.setBoardWriteDate(new Date().toString());

        board.setBoardSeq(1);
        boardRepository.save(board);

    }
}
