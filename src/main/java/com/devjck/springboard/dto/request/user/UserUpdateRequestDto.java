package com.devjck.springboard.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickName;

    private String password;

    private String name;

    private String address;

    private String number;

    private String mailAddress;

    @Builder
    public UserUpdateRequestDto(String nickName, String password, String name,
                                String address, String number, String mailAddress) {
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.address = address;
        this.number = number;
        this.mailAddress = mailAddress;
    }

}
