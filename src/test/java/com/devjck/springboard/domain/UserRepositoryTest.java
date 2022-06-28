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
	@org.junit.jupiter.api.Test
	@Transactional
	@Rollback(false)
	public void saveUserAndRead() {
		String userIdTest = "Billy Angel";
		String userPasswordTest = "Deep Dark Fantasy";
		String userNameTest = "빌리헤링턴";
		int userAgeTest = 69;
		char userGenderTest = 'G';
		String userAddressTest = "Boy Next Door";
		
		userRepository.save(User.builder()
				.userId(userIdTest)
				.userPassword(userPasswordTest)
				.userName(userNameTest)
				.userAge(userAgeTest)
				.userGender(userGenderTest)
				.userAddress(userAddressTest)
				.build()
		);
		
		List<User> user = userRepository.findAll();
		
		user.forEach((data) -> {
			System.out.println(data);
		});
		
	}
}
