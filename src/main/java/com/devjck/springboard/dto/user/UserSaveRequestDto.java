package com.devjck.springboard.dto.user;

import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.domain.user.enumType.Gender;
import com.devjck.springboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class UserSaveRequestDto {

    private String nickName;

    private String password;

    private String name;

    private String dateOfBirth;

    private Gender gender;

    private String address;

    private String number;

    private String mailAddress;

    private Authority authority;

    @Builder
    public UserSaveRequestDto(String nickName, String password, String name, String dateOfBirth, Gender gender,
                              String address, String number, String mailAddress, Authority authority) {
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.number = number;
        this.mailAddress = mailAddress;
        this.authority = authority;
    }

    public void setAuthority(Authority author) {
        this.authority = author;
    }

    public User toEntity() {
        return User.builder()
                .nickName(nickName)
                .password(password)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .gender(gender)
                .address(address)
                .number(number)
                .mailAddress(mailAddress)
                .authority(authority)
                .build();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==============================");
        sb.append("\nUSER INFO LOG");
        sb.append("\n" + this.getName());
        sb.append("\n" + this.getMailAddress());
        sb.append("\n" + this.getNickName());
        sb.append("\n" + this.getAuthority().getKey());
        sb.append("\n" + this.getAuthority().getValue());
        sb.append("\n==============================");

        return sb.toString();
    }
}
