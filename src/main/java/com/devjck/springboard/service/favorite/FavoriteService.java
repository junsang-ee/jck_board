package com.devjck.springboard.service.favorite;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.favorite.Favorite;
import com.devjck.springboard.domain.favorite.FavoriteRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.dto.favorite.FavoriteSaveRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    public Long save(FavoriteSaveRequestDto favoriteSaveRequestDto) {
        return favoriteRepository.save(favoriteSaveRequestDto.toEntity()).getFavoriteId();
    }


}
