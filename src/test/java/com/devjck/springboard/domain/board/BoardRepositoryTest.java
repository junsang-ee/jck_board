package com.devjck.springboard.domain.board;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.board.BoardSimpleResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
//    @After
//    public void cleanUp() {
//        boardRepository.deleteAll();
//    }

    @Test
    public void saveBoardAndRead() {
        //given
        String testTitle = "testTitle";
        String boardContents = "testBoardContents";
        String boardPassword = "1q2w3e4r5t";
        String boardOpenRange = "1";
        User user = userRepository.findAll().get(0);
        boardRepository.save(Board.builder()
                .writeUser(user)
                .title(testTitle)
                .content(boardContents)
                .password(boardPassword)
                .openRange(boardOpenRange)
                .build());
        //when
        List<Board> boards = boardRepository.findAll();
        Board board = boards.get(0);

        Assertions.assertThat(board.getContent()).isEqualTo(boardContents);
        Assertions.assertThat(board.getPassword()).isEqualTo(boardPassword);
        Assertions.assertThat(board.getOpenRange()).isEqualTo(boardOpenRange);
        Assert.assertNotNull(board.getCreatedAt());
    }

    @Test
    public void searchByTitleLikeTest() {
        String title = "그냥";
        List<Board> boards = boardRepository.findByTitleContains(title);
        System.out.println("board count : " + boards.size());
    }

    @Test
    public void getBoardByNickNameTest() {
        String nickName = "junsang";
        List<Board> boards = boardRepository.findByWriteUserNickName(nickName);
        List<BoardSimpleResponseDto> boardSimpleResponseDtos = new ArrayList<>();
        boards.stream().forEach(board -> {
            boardSimpleResponseDtos.add(new BoardSimpleResponseDto(board));
        });
        if (boards != null && boards.size() != 0) {
            for (int i = 0; i < boardSimpleResponseDtos.size(); i++)
                System.out.println("boardSimpleResponseDtos : " + boardSimpleResponseDtos.get(i));
        }

    }
}
