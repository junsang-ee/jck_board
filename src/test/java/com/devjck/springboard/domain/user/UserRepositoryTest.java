package com.devjck.springboard.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.domain.user.enumType.Gender;
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
	public void saveUserAndReadTest() {
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
	public void findByEmailTest() {
		String mailAddress = "test";
		boolean test = userRepository.existsByMailAddress(mailAddress);
		System.out.println("test : " + test);
	}

	@Test
	@Rollback(false)
	public void findByNickNameTest() {
		String nickName = "junsa";
		List<User> users = userRepository.findByNickNameContains(nickName);
		if (users != null) {
			System.out.println("user count : " + users.size());
			for (int i = 0; i < users.size(); i++) {
				System.out.println("user nick name : " + users.get(i).getNickName());
			}
		}
		else System.out.println("user is null..");

	}

	@Test
	@Rollback(false)
	public void updateLastedAccessTime() {
		Long userId = 1L;
		LocalDateTime currentTime = LocalDateTime.now();
		userRepository.updateLastAccessTime(currentTime, userId);


	}

}
