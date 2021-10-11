package com.weather.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/board")
public class BoardController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/list")
    public ResponseEntity list() {
        return ResponseEntity.ok("OK");
    }
}
