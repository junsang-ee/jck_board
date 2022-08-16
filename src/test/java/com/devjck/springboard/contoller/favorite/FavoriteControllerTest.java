package com.devjck.springboard.contoller.favorite;

import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.favorite.FavoriteRepository;
import com.devjck.springboard.service.board.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FavoriteControllerTest {
    final String URL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private BoardService boardService;
//    @Test
//    public void saveFavoriteTest() throws Exception{
//        String url = URL + port + "/api/favorite";
//
//        Board board = boardService.
//    }
}
