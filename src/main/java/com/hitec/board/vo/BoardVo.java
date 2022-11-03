package com.hitec.board.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardVo {
    private long id;
    private String title;
    private String content;

    private String userName;
}
