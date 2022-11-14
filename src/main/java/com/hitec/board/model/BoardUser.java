package com.hitec.board.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardUser{

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    String name;

    @Column(name = "preferred_username")
    String preferred_username;

    @Column(name = "email")
    String email;
}
