package com.devjck.springboard.service.board;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.board.Board;
import com.devjck.springboard.domain.board.BoardRepository;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.dto.request.board.BoardSaveRequestDto;
import com.devjck.springboard.dto.request.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto, Authentication authentication) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        User writer = principal.getUser();

        boardSaveRequestDto = BoardSaveRequestDto.builder()
                .title(boardSaveRequestDto.getTitle())
                .content(boardSaveRequestDto.getContent())
                .password(boardSaveRequestDto.getPassword())
                .openRange(boardSaveRequestDto.getOpenRange())
                .writeUser(writer)
                .build();

        return boardRepository.save(boardSaveRequestDto.toEntity()).getBoardId();
    }

    @Transactional
    public Long update(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("!!!!!! BoardService update -> don`t exists board"));

        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent(),
                boardUpdateRequestDto.getPassword(), boardUpdateRequestDto.getOpenRange());

        return boardId;
    }

    @Transactional
    public Long delete(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("!!!!!! BoardService delete -> don`t exists board"));

        board.delete(boardUpdateRequestDto.getStatus());

        return boardId;
    }

    @Transactional
    public List<Board> searchAndGetByBoardTitle(String title) {
        return boardRepository.findByTitleContains(title);
    }

    @Transactional
    public List<Board> searchAndGetBoardByUserNickName(String nickName) {
        return boardRepository.findByWriteUserNickName(nickName);

    }
}
