package com.hitec.board.service;

import java.util.List;

import com.hitec.board.model.Board;
import com.hitec.board.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
   
    BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    public Board getBoard(Long id){
        return boardRepository.findById(id).orElseThrow();
    }

    public Board setBoard(Board board){
        return boardRepository.save(board);
    }

    public void removeBoard(Long id){
        boardRepository.findById(id).ifPresent(board -> boardRepository.delete(board));
    }

}
