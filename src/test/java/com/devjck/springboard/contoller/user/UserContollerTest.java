package com.devjck.springboard.contoller.user;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserContollerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() throws Exception {
        //given
        String nickname = "saveUserTestNickName";
        String password = "saveUserTestPassword";
        String name = "saveUserTestName";
        int age = 29;
        String gender = "M";
        String address = "saveUserTestAddress";
        String number = "saveUserTestNumber";
        String mailAddress = "saveUserTestMailAddress";

        UserSaveRequestDto userSaveRequestDto =
                UserSaveRequestDto.builder()
                .nickName(nickname)
                .password(password)
                .name(name)
                .age(age)
                .gender(gender)
                .address(address)
                .number(number)
                .mailAddress(mailAddress)
                .build();

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/user/insert", userSaveRequestDto, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.get(0).getNickName()).isEqualTo(nickname);
    }
}
