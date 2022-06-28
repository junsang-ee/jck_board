package com.devjck.springboard.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userSeq;
	
	@Column
	private String userId;
	
	@Column
	private String userPassword;
	
	@Column
	private String userName;
	
	@Column
	private int userAge;
	
	@Column
	private char userGender;
	
	@Column
	private String userAddress;
	
	@Builder
	public User(String userId, String userPassword, String userName, int userAge, char userGender, String userAddress) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userAddress = userAddress;
	}
}
