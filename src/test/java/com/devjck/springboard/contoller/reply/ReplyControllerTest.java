package com.devjck.springboard.contoller.reply;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.reply.ReplyRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.reply.ReplySaveRequestDto;
import com.devjck.springboard.dto.reply.ReplyUpdateRequestDto;
import org.assertj.core.api.Assertions;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReplyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveReplyTest() throws Exception {

        User replyUser = userRepository.findById(2L).orElseThrow(
                () -> new IllegalArgumentException("User is Null"));
        Board parentBoard = boardRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("board is Null"));
        String content = "testReplyContent2";

        ReplySaveRequestDto replySaveRequestDto =
                ReplySaveRequestDto.builder()
                .parentBoard(parentBoard)
                .content(content)
                .replyUser(replyUser)
                .build();

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/reply/insert",
                replySaveRequestDto,
                Long.class
        );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        Reply reply = replyRepository.findAll().get(0);

        Assertions.assertThat(reply.getParentBoard().getBoardId()).isEqualTo(parentBoard.getBoardId());
        Assertions.assertThat(reply.getReplyUser().getUserId()).isEqualTo(replyUser.getUserId());
        Assertions.assertThat(reply.getContent()).isEqualTo(content);
    }

    @Test
    public void updateTest() {
        String content = "updateContent";

        Reply updateReply = replyRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("is Null")
        );

        Long updateReplyId = updateReply.getReplyId();

        ReplyUpdateRequestDto replyUpdateRequestDto =
                ReplyUpdateRequestDto.builder()
                        .content(content)
                        .build();

        String url = "http://localhost:" + port + "/reply/update/" + updateReplyId;

        HttpEntity<ReplyUpdateRequestDto> requestEntity =
                new HttpEntity<>(replyUpdateRequestDto);

        ResponseEntity<Long> responseEntity =
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Reply reply = replyRepository.findById(updateReplyId).orElse(new Reply());

        Assertions.assertThat(reply.getContent()).isEqualTo(content);

    }

}
