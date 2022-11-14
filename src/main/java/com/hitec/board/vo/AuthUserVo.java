package com.hitec.board.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthUserVo{
    String name;
    String preferred_username;
    String username;
    String email;
    String id;
}
