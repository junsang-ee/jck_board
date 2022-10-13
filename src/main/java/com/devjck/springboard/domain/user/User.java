package com.devjck.springboard.domain.user;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.common.BaseEntity;
import com.devjck.springboard.domain.reply.Reply;
import com.devjck.springboard.domain.tracking.Tracking;
import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.domain.user.enumType.Gender;
import com.devjck.springboard.domain.user.enumType.Status;
import com.devjck.springboard.domain.user.converter.StatusConverter;
import com.devjck.springboard.domain.user.converter.AuthorityConverter;
import com.devjck.springboard.domain.user.converter.GenderConverter;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    // GenerationType.SEQUENCE 로 설정 시 에러 발생하여 IDENTITY로 변경
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String dateOfBirth;

    @Convert(converter = GenderConverter.class)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false, unique = true)
    private String mailAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "writeUser", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "replyUser", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> reply = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "requestUser", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tracking> requestUser = new ArrayList<>();

    @Convert(converter = AuthorityConverter.class)
    @Column(nullable = false)
    private Authority authority;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status")
    private Status status = Status.NORMAL;

    @Column
    @ColumnDefault("null")
    private LocalDateTime lastAccessTime;

    @Builder
    public User(String nickName, String password, String name, String dateOfBirth, Gender gender,
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
