package com.hitec.board.service;

import com.hitec.board.model.BoardUser;
import com.hitec.board.repository.BoardUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardUserService {
   
    BoardUserRepository userRepository;

    @Autowired
    public BoardUserService(BoardUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public BoardUser setUser(BoardUser user){
        return userRepository.save(user);
    }

    public BoardUser getUser(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public BoardUser getUserByName(String userName){
        return userRepository.findBoardUserByUsername(userName);
    }

}
