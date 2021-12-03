package com.weather.board;

import com.weather.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<Board> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }
}
