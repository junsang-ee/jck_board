package com.devjck.springboard.domain.favorite;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteRepositoryTest {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void saveFavoriteTest() {
        Board board = boardRepository.findAll().get(0);
        User user = userRepository.findAll().get(0);
        favoriteRepository.save(Favorite.builder().favoriteBoard(board).likedUser(user).build());
    }
}
