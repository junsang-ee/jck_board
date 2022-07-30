package com.devjck.springboard.contoller.nestedReply;

import com.devjck.springboard.domain.nestedReply.NestedReply;
import com.devjck.springboard.domain.nestedReply.NestedReplyRepository;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.reply.ReplyRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.nestedReply.NestedReplySaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NestedReplyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NestedReplyRepository nestedReplyRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    final String HOST = "http://localhost:";

    @Test
    public void saveNestedReplyTest() throws Exception {

        String url = HOST + port + "/api/nested-reply";

        User nestedReplyWriter = userRepository.findById(1L).orElseThrow(() ->
                new IllegalArgumentException("!!!!!! NestedReplyController saveNestedReplyTest -> user is null"));

        Reply parentReply = replyRepository.findById(4L).orElseThrow(() ->
                new IllegalArgumentException("!!!!!! NestedReplyController saveNestedReplyTest -> reply is null"));

        String content = "NestedReplyTest";

        NestedReplySaveRequestDto nestedReplySaveRequestDto =
                NestedReplySaveRequestDto.builder()
                        .parentReply(parentReply)
                        .content(content)
                        .nestedReplyWriter(nestedReplyWriter)
                        .build();

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().registerModule(new JavaTimeModule())
                        .writeValueAsString(nestedReplySaveRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<NestedReply> all = nestedReplyRepository.findAll();

        int idx = all.size()-1;

        Assertions.assertThat(all.get(idx).getParentReply().getReplyId()).isEqualTo(parentReply.getReplyId());
        Assertions.assertThat(all.get(idx).getContent()).isEqualTo(content);
        Assertions.assertThat(all.get(idx).getNestedReplyWriter().getUserId()).isEqualTo(nestedReplyWriter.getUserId());

    }

}
