package com.devjck.springboard.domain.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
		String dateOfBirth = "940316";
		Gender genderTest = Gender.M;
		String userAddressTest = "Boy Next Door";
		String testNumber = "010-4305-34521";
		String testMailAddress = "gmail.com";
		Authority authority = Authority.ADMIN;

		userRepository.save(User.builder()
				.nickName(testNickName)
				.password(userPasswordTest)
				.name(userNameTest)
				.dateOfBirth(dateOfBirth)
				.gender(genderTest)
				.address(userAddressTest)
				.number(testNumber)
				.mailAddress(testMailAddress)
			 	.authority(authority)
				.build()
		);
		
		List<User> user = userRepository.findAll();
		
		user.forEach((data) -> {
			System.out.println(data);
		});
		
	}

	@Test
	@Rollback(false)
	public void testFindByEmail() {
		String mailAddress = "test";
		boolean test = userRepository.existsByMailAddress(mailAddress);
		System.out.println("test : " + test);
	}

}
