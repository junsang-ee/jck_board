package com.devjck.springboard.dto.user;

import com.devjck.springboard.domain.user.Gender;
import com.devjck.springboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String nickName;

    private String password;

    private String name;

    private int age;

    private Gender gender;

    private String address;

    private String number;

    private String mailAddress;

    @Builder
    public UserSaveRequestDto(String nickName, String password, String name, int age,
                              Gender gender, String address, String number, String mailAddress) {
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.number = number;
        this.mailAddress = mailAddress;
    }

    public User toEntity() {
        return User.builder()
                .nickName(nickName)
                .password(password)
                .name(name)
                .age(age)
                .gender(gender)
                .address(address)
                .number(number)
                .mailAddress(mailAddress)
                .build();
    }

}
