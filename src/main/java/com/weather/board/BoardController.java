package com.weather.board;

import com.weather.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Board> list = boardService.selectBoardList();

        mv.addObject("list", list);

        return mv;
    }
}
