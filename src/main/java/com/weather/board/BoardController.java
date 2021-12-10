package com.weather.board;

import com.weather.board.domain.Board;
import com.weather.common.enumeration.CityType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<?> selectBoardList(Board board, Model model) throws Exception{
        List<Board> list = boardService.selectBoardList(board);
        model.addAttribute("list", list);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/write")
    public ResponseEntity<?> insertBoard(@RequestBody Board board) throws Exception{
        boardService.insertBoard(board);
        return ResponseEntity.ok("success");
    }
}
