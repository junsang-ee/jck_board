package com.devjck.springboard.contoller.board;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import com.devjck.springboard.dto.board.BoardUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
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

    @Autowired
    private UserRepository userRepository;

    final String URL = "http://localhost:";
//    @After
//    public void tearDown() throws Exception {
//        boardRepository.deleteAll();
//    }

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        session = new MockHttpSession();
        session.setAttribute("user", userRepository.findById(6L).orElseThrow(
                () -> new IllegalArgumentException("is null")));
    }

    @After
    public void clean() {
        session.clearAttributes();
    }

    @Test
    public void saveBoardTest() throws Exception {
        //given
        User writer = userRepository.findAll().get(2);

        String url = URL + port + "/api/board";

        String title = "title_0715";
        String content = "content_0715";
        String password = "password_00715";
        String openRange = "0";
        BoardSaveRequestDto boardSaveRequestDto =
                BoardSaveRequestDto.builder()
//                    .writeUser(writer)
                    .title(title)
                    .content(content)
                    .password(password)
                    .openRange(openRange)
                    .build();

        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
//                "http://localhost:" +  port + "/api/board", boardSaveRequestDto, Long.class);

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(boardSaveRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
//        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Board> boards= boardRepository.findAll();
        int idx = boards.size()-1;
        Assertions.assertThat(boards.get(idx).getTitle()).isEqualTo(title);
        Assertions.assertThat(boards.get(idx).getContent()).isEqualTo(content);
        Assertions.assertThat(boards.get(idx).getPassword()).isEqualTo(password);
        Assertions.assertThat(boards.get(idx).getOpenRange()).isEqualTo(openRange);
    }

    @Test
    public void updateBoardTest() {
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

        String url = "http://localhost:" + port + "/api/board/" + updateBoardSeq;

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

    @Test
    public void searchByTitleContainsTest() {
        String title = "잭스";
        String url = "http://localhost:" + port + "/api/board/searchByBoardTitle";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        HttpEntity<?> header = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("title", title)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();

//        List<Board> boards = restTemplate.getForObject(uri, List.class);
        ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, header, List.class);
//        if (boards != null) System.out.println("boards count : " + boards.size());
//        else System.out.println("board is nul..");
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(responseEntity.getBody()).isEqualTo(true);
        System.out.println("HttpStatus :: " + responseEntity.getStatusCode());
        System.out.println("Body  :: " + responseEntity.getBody());
    }

    @Test
    public void searchByUserContainsTest() {
        String title = "잭스";
        String url = "http://localhost:" + port + "/api/board/searchByBoardTitle";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        HttpEntity<?> header = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("title", title)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();

//        List<Board> boards = restTemplate.getForObject(uri, List.class);
        ResponseEntity<?> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, header, List.class);
//        if (boards != null) System.out.println("boards count : " + boards.size());
//        else System.out.println("board is nul..");
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(responseEntity.getBody()).isEqualTo(true);
        System.out.println("HttpStatus :: " + responseEntity.getStatusCode());
        System.out.println("Body  :: " + responseEntity.getBody());
    }

    @Test
    public void getBoardByNickNameTest() {
        String nickName = "junsang";
        String url = URL + port + "/api/board/seachBoardByNickName";
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nickName", nickName)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        List<Board> boards = restTemplate.getForObject(uri, List.class);
        if (boards != null) {
            System.out.println("board count : " + boards.size());
            for (int i = 0; i < boards.size(); i++) {
                System.out.println("board content : " + boards.get(i));
            }
        }
        else System.out.println("board is null..");
    }

}
