package com.devjck.springboard.dto.request.favorite;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.favorite.Favorite;
import com.devjck.springboard.domain.user.User;
import lombok.Builder;

public class FavoriteSaveRequestDto {
    private User likedUser;

    private Board favoriteBoard;

    @Builder
    public FavoriteSaveRequestDto(PrincipalDetails principalDetails, Board board) {
        this.likedUser = principalDetails.getUser();
        this.favoriteBoard = board;
    }

    public Favorite toEntity() {
        return Favorite.builder().favoriteBoard(favoriteBoard).likedUser(likedUser).build();
    }
}
