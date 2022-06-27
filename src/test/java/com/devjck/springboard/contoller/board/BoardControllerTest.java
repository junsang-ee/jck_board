package com.devjck.springboard.contoller.board;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;

//    @After
//    public void tearDown() throws Exception {
//        boardRepository.deleteAll();
//    }
    @Test
    public void saveBoardTest() throws Exception {
        //given
        int boardWriterId = 1;
        String boardContents = "testContents";
        String boardPassword = "1q2w3e4r5t";
        String boardOpenRange = "0";
        BoardSaveRequestDto boardSaveRequestDto =
                BoardSaveRequestDto.builder()
                .boardWriterId(boardWriterId)
                .boardContents(boardContents)
                .boardPassword(boardPassword)
                .boardOpenRange(boardOpenRange)
                .build();

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
                "http://localhost:" +  port + "/board", boardSaveRequestDto, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Board> boards= boardRepository.findAll();
        Assertions.assertThat(boards.get(0).getBoardContents()).isEqualTo(boardContents);
        Assertions.assertThat(boards.get(0).getBoardPassword()).isEqualTo(boardPassword);
        Assertions.assertThat(boards.get(0).getBoardOpenRange()).isEqualTo(boardOpenRange);
    }

}
