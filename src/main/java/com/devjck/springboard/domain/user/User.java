package com.devjck.springboard.domain.user;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Convert(converter = UserGenderConverter.class)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false, unique = true)
    private String mailAddress;

    @OneToMany(mappedBy = "writeUser", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Board> boards;

    @Builder
    public User(String nickName, String password, String name, int age, Gender gender,
            String address, String number, String mailAddress) {
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.number = number;
        this.mailAddress = mailAddress;
    }

    public void update(String nickName, String password, String name, String address,
                       String number, String mailAddress) {
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.address = address;
        this.number = number;
        this.mailAddress = mailAddress;
    }

}
