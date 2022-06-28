package com.devjck.springboard.domain;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.annotations.SQLDeleteAll;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

//    @After
//    public void cleanUp() {
//        boardRepository.deleteAll();
//    }

    @Test
    public void saveBoardAndRead() {
        //given

        int boardWriteId = 1;
        String boardContents = "testBoardContents";
        String boardPassword = "1q2w3e4r5t";
        String boardOpenRange = "1";

        boardRepository.save(Board.builder()
                .boardWriterId(boardWriteId)
                .boardContents(boardContents)
                .boardPassword(boardPassword)
                .boardOpenRange(boardOpenRange).build());
        //when
        List<Board> boards = boardRepository.findAll();
        Board board = boards.get(0);
        Assertions.assertThat(board.getBoardWriterId()).isEqualTo(boardWriteId);
        Assertions.assertThat(board.getBoardContents()).isEqualTo(boardContents);
        Assertions.assertThat(board.getBoardPassword()).isEqualTo(boardPassword);
        Assertions.assertThat(board.getBoardOpenRange()).isEqualTo(boardOpenRange);

    }

}
