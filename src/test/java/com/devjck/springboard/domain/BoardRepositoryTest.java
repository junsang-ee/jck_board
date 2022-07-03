package com.devjck.springboard.domain;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.dto.board.BoardUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.hibernate.annotations.SQLDeleteAll;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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
        String testTitle = "testTitle";
        String boardContents = "testBoardContents";
        String boardPassword = "1q2w3e4r5t";
        String boardOpenRange = "1";
        User user = new User();
        boardRepository.save(Board.builder()
                .writeUser(user)
                .title(testTitle)
                .content(boardContents)
                .password(boardPassword)
                .openRange(boardOpenRange)
                .build());
        //when
        List<Board> boards = boardRepository.findAll();
        Board board = boards.get(0);

        Assertions.assertThat(board.getContent()).isEqualTo(boardContents);
        Assertions.assertThat(board.getPassword()).isEqualTo(boardPassword);
        Assertions.assertThat(board.getOpenRange()).isEqualTo(boardOpenRange);
        Assert.assertNotNull(board.getCreatedAt());
    }




}
