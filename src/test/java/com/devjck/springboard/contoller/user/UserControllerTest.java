package com.devjck.springboard.contoller.user;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import org.junit.Test;
import org.assertj.core.api.Assertions;
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

import java.util.List;
import java.util.Optional;

/**
 * 테스트 코드 관련 어노테이션 구분
 * @author hyunj
 *
 * @RunWith(SpringRunner.class)
 * 		- JUnit4에서 사용 import org.junit.runner.RunWith
 * @ExtendWith(SpringExtension.class)
 * 		- JUnit5에서 사용 import org.junit.jupiter.api.extension.ExtendWith
 *
 * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
 *
 * JUnit4에서는 SpringRunner라는 스프링 실행자를 사용한다.
 *
 * @SpringBootTest
 * 		- 스프링부트 어플리케이션 테스트를 실행할 때 필요한 대부분의 의존성을 제공해준다.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
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


    @Test
    public void updateUserTest() {
        String nickName = "updateNickName";
        String password = "updateTestPassword";
        String name = "testUpdateName";
        String address = "testUpdateAddress";
        String number = "testUpdateNumber";
        String mailAddress = "testUpdateMailAddress";

        User user = userRepository.findAll().get(0);
        Long userId = user.getUserId();
        UserUpdateRequestDto userUpdateRequestDto = UserUpdateRequestDto.builder()
                .nickName(nickName)
                .password(password)
                .name(name)
                .address(address)
                .number(number)
                .mailAddress(mailAddress)
                .build();
        String url = "http://localhost:" + port + "/user/update/" + userId;

        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(userUpdateRequestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
            requestEntity, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<User> all = userRepository.findAll();
        Assertions.assertThat(all.get(0).getNickName()).isEqualTo(nickName);
        Assertions.assertThat(all.get(0).getPassword()).isEqualTo(password);
        Assertions.assertThat(all.get(0).getAddress()).isEqualTo(address);
    }
}
