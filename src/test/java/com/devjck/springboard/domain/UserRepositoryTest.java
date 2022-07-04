package com.devjck.springboard.domain;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	public void saveUserAndRead() {
		String testNickName = "testNickName";
		String userPasswordTest = "Deep Dark Fantasy";
		String userNameTest = "빌리헤링턴";
		int userAgeTest = 69;
		String userGenderTest = "G";
		String userAddressTest = "Boy Next Door";
		String testNumber = "010-4305-3451";
		String testMailAddress = "gmail.com";
		
		userRepository.save(User.builder()
				.nickName(testNickName)
				.password(userPasswordTest)
				.name(userNameTest)
				.age(userAgeTest)
				.gender(userGenderTest)
				.address(userAddressTest)
				.number(testNumber)
				.mailAddress(testMailAddress)
				.build()
		);
		
		List<User> user = userRepository.findAll();
		
		user.forEach((data) -> {
			System.out.println(data);
		});
		
	}

}
