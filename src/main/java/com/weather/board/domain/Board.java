package com.weather.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Board {
    private long boardId;
    private long userId;

    private String title;
    private String contents;
    private String userName;
    private int hit;

    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    private String deleteYn;
}
