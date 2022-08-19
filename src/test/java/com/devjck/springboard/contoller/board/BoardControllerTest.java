package com.devjck.springboard.contoller.board;

import com.devjck.springboard.config.jwt.JwtProperties;
import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.board.BoardSaveRequestDto;
import com.devjck.springboard.dto.board.BoardUpdateRequestDto;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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

    private String JWT_TOKEN = "";
//    @After
//    public void tearDown() throws Exception {
//        boardRepository.deleteAll();
//    }

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

//        session = new MockHttpSession();
//        session.setAttribute("user", userRepository.findById(1L).orElseThrow(() ->
//                new IllegalArgumentException("is null")));

            String url = URL + port + "/api/user/login";

            String mailAddress = "cheol";
            String password = "cheoltest";

            UserSaveRequestDto userSaveRequestDto =
                    UserSaveRequestDto.builder()
                            .mailAddress(mailAddress)
                            .password(password)
                            .build();

            ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
                    url, userSaveRequestDto, Long.class);

        JWT_TOKEN = responseEntity.getHeaders().getFirst(JwtProperties.HEADER_STRING);
    }

    @After
    public void clean() {
        JWT_TOKEN = "";
    }

    @Test
    public void saveBoardTest() throws Exception {
        String url = URL + port + "/api/board";

        System.out.println("url : " + url);
        System.out.println("token : " + JWT_TOKEN);

        String title = "title_0819";
        String content = "content_0819";
        String password = "password_0819";
        String openRange = "0";
        BoardSaveRequestDto boardSaveRequestDto =
                BoardSaveRequestDto.builder()
                        .title(title)
                        .content(content)
                        .password(password)
                        .openRange(openRange)
                        .build();

                mvc.perform(MockMvcRequestBuilders.post(url)
                    .header(JwtProperties.HEADER_STRING, JWT_TOKEN)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(new ObjectMapper().writeValueAsString(boardSaveRequestDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Board> boards = boardRepository.findAll();
        int idx = boards.size() - 1;
        Assertions.assertThat(boards.get(idx).getTitle()).isEqualTo(title);
        Assertions.assertThat(boards.get(idx).getContent()).isEqualTo(content);
        Assertions.assertThat(boards.get(idx).getPassword()).isEqualTo(password);
        Assertions.assertThat(boards.get(idx).getOpenRange()).isEqualTo(openRange);
    }

    @Test
    public void updateBoardTest() throws Exception {
        long updateBoardSeq = 2L;
        String url = URL + port + "/api/board/" + updateBoardSeq;

        String title = " 0727";
        String content = "update_content";
        String password = "update_test_password";
        String openRange = "2";

        boardRepository.findById(updateBoardSeq).orElseThrow(
                () -> new IllegalArgumentException("null"));

        BoardUpdateRequestDto boardUpdateRequestDto = BoardUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .openRange(openRange)
                .password(password)
                .build();

        mvc.perform(MockMvcRequestBuilders.put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(boardUpdateRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Board updateBoard = boardRepository.findById(updateBoardSeq).orElseThrow(() ->
                new IllegalArgumentException("null"));

        Assertions.assertThat(updateBoard.getContent()).isEqualTo(content);
        Assertions.assertThat(updateBoard.getPassword()).isEqualTo(password);
        Assertions.assertThat(updateBoard.getOpenRange()).isEqualTo(openRange);
    }

    @Test
    public void deleteBoardTest() throws Exception {

        long deleteBoardSeq = 2L;
        int status = 1;

        boardRepository.findById(deleteBoardSeq).orElseThrow(
                () -> new IllegalArgumentException("null"));

        BoardUpdateRequestDto boardUpdateRequestDto = BoardUpdateRequestDto.builder()
                .status(status)
                .build();

        String url = URL + port + "/api/board/" + deleteBoardSeq;

        mvc.perform(MockMvcRequestBuilders.delete(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(boardUpdateRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Board updateBoard = boardRepository.findById(deleteBoardSeq).orElseThrow(() ->
                new IllegalArgumentException("null"));

        Assertions.assertThat(updateBoard.getStatus()).isEqualTo(status);
    }

    @Test
    public void searchAndGetByBoardTitleTest() {
        String title = "titleTest1";
        String url = "http://localhost:" + port + "/api/board/searchAndGetByBoardTitleTest";

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
    public void searchAndGetBoardByUserNickNameTest() {
        String nickName = "testNickNamebobo1";
        String url = URL + port + "/api/board/searchAndGetBoardByUserNickName";
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
        } else System.out.println("board is null..");
    }

}
