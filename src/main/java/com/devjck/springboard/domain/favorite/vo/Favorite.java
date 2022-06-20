package com.devjck.springboard.domain.favorite.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "favorite")
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    private int favoriteSeq;

    @Column
    private int favoriteBoardId;

    @Column
    private int favoriteUserId;
}
