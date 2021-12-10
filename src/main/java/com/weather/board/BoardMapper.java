package com.weather.board;

import com.weather.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    /**
     * 게시글 생성
     * @param board
     */
    void insertBoard(Board board) throws Exception;

    /**
     * 게시글 수정
     * @param board
     * @return
     */
    int updateBoard(Board board) throws Exception;

    /**
     * 게시글 삭제
     * @param boardId
     * @return
     */
    int deleteBoard(long boardId) throws Exception;

    /**
     * 게시글 상세 조회
     * @param boardId
     * @return
     */
    Board selectBoardDetail(long boardId) throws Exception;

    /**
     * 게시글 목록 조회
     */
    List<Board> selectBoardList(Board board) throws Exception;
}
