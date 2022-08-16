package com.devjck.springboard.controller.favorite;

import com.devjck.springboard.dto.favorite.FavoriteSaveRequestDto;
import com.devjck.springboard.dto.reply.ReplySaveRequestDto;
import com.devjck.springboard.dto.reply.ReplyUpdateRequestDto;
import com.devjck.springboard.service.favorite.FavoriteService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/api/favorite")
    public Long save(@RequestBody FavoriteSaveRequestDto favoriteSaveRequestDto) {
        return favoriteService.save(favoriteSaveRequestDto);
    }

//    @PutMapping("/api/favorite/{favoriteId}")
//    public Long update(@PathVariable Long favoriteId, @RequestBody FavoriteRequestDto favoriteRequestDto) {
//        return favoriteService.update(replyId, replyUpdateRequestDto);
//    }
}
