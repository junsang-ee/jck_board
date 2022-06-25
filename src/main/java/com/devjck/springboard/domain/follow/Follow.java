package com.devjck.springboard.domain.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "follow")
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    private int followSeq;
    @Column
    private int followedUserId;
    @Column
    private int followingUserId;
    @Column
    private int followedDate;
    @Column
    private int followStatus;
}
