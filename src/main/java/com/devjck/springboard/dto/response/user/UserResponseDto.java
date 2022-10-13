package com.devjck.springboard.dto.response.user;

import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.domain.user.enumType.Gender;
import com.devjck.springboard.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserResponseDto {
    private Long userId;

    private String nickName;

    private String name;

    private String dateOfBirth;

    private Gender gender;

    private String address;

    private String number;

    private String mailAddress;

    private Authority authority;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
        this.name = user.getName();
        this.dateOfBirth = user.getDateOfBirth();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.number = user.getNumber();
        this.mailAddress = user.getMailAddress();
        this.authority = user.getAuthority();
    }

}
