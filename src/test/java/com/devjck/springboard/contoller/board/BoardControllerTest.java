package com.devjck.springboard.contoller.board;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import com.devjck.springboard.dto.board.BoardUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
        User writer = null;
        String content = "testContents";
        String password = "1q2w3e4r5t";
        String openRange = "0";
        BoardSaveRequestDto boardSaveRequestDto =
                BoardSaveRequestDto.builder()
                .writer(writer)
                .content(content)
                .password(password)
                .openRange(openRange)
                .build();

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
                "http://localhost:" +  port + "/board", boardSaveRequestDto, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Board> boards= boardRepository.findAll();
        Assertions.assertThat(boards.get(0).getContent()).isEqualTo(content);
        Assertions.assertThat(boards.get(0).getPassword()).isEqualTo(password);
        Assertions.assertThat(boards.get(0).getOpenRange()).isEqualTo(openRange);
    }

    @Test
    public void updateTest() {
        String title = " updated_title";
        String content = "update_content";
        String password = "update_test_password";
        String openRange = "2";

        List<Board> boards = boardRepository.findAll();
        Board updateBoard = boards.get(0);
        Long updateBoardSeq = updateBoard.getBoardId();

        System.out.println("updateBoard ::::::: " + updateBoard);

        BoardUpdateRequestDto boardUpdateRequestDto = BoardUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .openRange(openRange)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/board/update/" + updateBoardSeq;

        HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(boardUpdateRequestDto);


        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Board> all = boardRepository.findAll();

        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
        Assertions.assertThat(all.get(0).getPassword()).isEqualTo(password);
        Assertions.assertThat(all.get(0).getOpenRange()).isEqualTo(openRange);

    }

}
