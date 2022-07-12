package com.devjck.springboard.contoller.user;

import com.devjck.springboard.domain.user.Authority;
import com.devjck.springboard.domain.user.Gender;
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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
        String nickname = "testNickNamebobo1";
        String password = "saveUserTestPassword";
        String name = "testName1";
        String dateOfBirth = "940316";
        Gender gender = Gender.M;
        String address = "saveUserTestAddress";
        String number = "010-4305-3453";
        String mailAddress = "testmail_address1";
        Authority authority = Authority.USER;
        System.out.println("gender : " + gender);
        System.out.println("authority : " + authority);

        UserSaveRequestDto userSaveRequestDto =
                UserSaveRequestDto.builder()
                .nickName(nickname)
                .password(password)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .address(address)
                .number(number)
                .mailAddress(mailAddress)
                .authority(authority)
                .build();

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/user/", userSaveRequestDto, Long.class);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.get(0).getNickName()).isEqualTo(nickname);
    }


    @Test
    public void updateUserTest() {
        String nickName = "updateNickName1";
        String password = "updateTestPassword1";
        String name = "testUpdateName1";
        String address = "testUpdateAddress1";
        String number = "testUpdateNumber1";
        String mailAddress = "testUpdateMailAddress1";

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
        String url = "http://localhost:" + port + "/api/user/" + userId;

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

    @Test
    public void testValidateEmail() {
        String mailAddress = "testmail_address";
        String url = "http://localhost:" + port + "/api/user/existsByMailAddress";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        HttpEntity<?> header = new HttpEntity<>(headers);
        //when

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                                    .queryParam("mailAddress", mailAddress)
                                    .encode(StandardCharsets.UTF_8)
                                    .build()
                                    .toUri();
        //test1 (getForObject) return object.
//        boolean responseEntity= restTemplate.getForObject(uri, boolean.class);

        //test2 (getForEntity) return object.
//        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(uri, boolean.class);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, header, Boolean.class);

        //then
        // test1 => result(getForObject)
//        if (responseEntity) System.out.println("true!");
//        else System.out.println("false!!");

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(true);
        System.out.println("HttpStatus :: " + responseEntity.getStatusCode());
        System.out.println("Body  :: " + responseEntity.getBody());

    }

    @Test
    public void testValidateNickName() {
        String nickName = "testNickNamebobo1";
        String url = "http://localhost:" + port + "/api/user/existsByNickName";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        HttpEntity<?> header = new HttpEntity<>(headers);
        //when

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nickName", nickName)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        //test1 (getForObject) return object.
//        boolean responseEntity= restTemplate.getForObject(uri, boolean.class);

        //test2 (getForEntity) return object.
//        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(uri, boolean.class);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, header, Boolean.class);

        //then
        // test1 => result(getForObject)
//        if (responseEntity) System.out.println("true!");
//        else System.out.println("false!!");

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(true);
        System.out.println("HttpStatus :: " + responseEntity.getStatusCode());
        System.out.println("Body  :: " + responseEntity.getBody());

    }
}
