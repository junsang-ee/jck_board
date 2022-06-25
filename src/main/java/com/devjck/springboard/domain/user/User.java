package com.devjck.springboard.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int userSeq;

    @Column
    private String userId;

    @Column
    private String userPassword;

    @Column
    private String userName;

    @Column
    private int userAge;

    @Column
    private String userGender;

    @Column
    private String userAddress;
}
