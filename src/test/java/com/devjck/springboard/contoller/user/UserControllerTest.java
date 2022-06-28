package com.devjck.springboard.contoller.user;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

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
	TestRestTemplate restTemplate;
	
	//@Autowired
	
}
