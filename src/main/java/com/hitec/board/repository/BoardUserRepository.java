package com.hitec.board.repository;

import com.hitec.board.model.BoardUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long>{
    public BoardUser findBoardUserByUsername(String userName);
}
