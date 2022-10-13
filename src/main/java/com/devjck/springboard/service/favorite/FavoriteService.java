package com.devjck.springboard.service.favorite;

import com.devjck.springboard.domain.favorite.FavoriteRepository;
import com.devjck.springboard.dto.request.favorite.FavoriteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
