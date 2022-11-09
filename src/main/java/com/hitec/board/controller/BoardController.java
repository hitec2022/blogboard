package com.hitec.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.hitec.board.model.Board;
import com.hitec.board.model.BoardUser;
import com.hitec.board.service.BoardService;
import com.hitec.board.service.BoardUserService;
import com.hitec.board.vo.BoardVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = {"http://localhost:3000","http://175.209.56.53:3000"}, maxAge=3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PUT} )
@RestController
public class BoardController {

    BoardService boardService;
    BoardUserService boardUserService;

    @Autowired
    public BoardController(BoardService boardService, BoardUserService boardUserService){
        this.boardService = boardService;
        this.boardUserService = boardUserService;
    }

    @GetMapping("/boards")
    public List<BoardVo> getBoardList(){
        List<Board> boardList = boardService.boardList();

        List<BoardVo> rtnList = new ArrayList<>();
        for(Board board : boardList){
            rtnList.add(new BoardVo(board.getId(), board.getTitle(), board.getContent(), board.getBoardUser().getUsername()));
        }

        return rtnList;
    }

    @GetMapping("/board/{boardId}")
    public BoardVo getBoard(@PathVariable Long boardId){
        Board board = boardService.getBoard(boardId);
        return new BoardVo(board.getId(), board.getTitle(), board.getContent(), board.getBoardUser().getUsername());
    }

    @PostMapping("/board")
    @Transactional
    public BoardVo setBoard(@RequestBody BoardVo boardVo){
        //TODO username이 null이면 저장하지 않게  
        //TODO username이 중복이 있는지 확인 
        BoardUser boardUser = boardUserService.setUser(new BoardUser(null, boardVo.getUserName()));
        Board board = boardService.setBoard(new Board(null, boardVo.getTitle(), boardVo.getContent(), boardUser));
        return new BoardVo(board.getId(), board.getTitle(), board.getContent(), board.getBoardUser().getUsername());
    }

    @PutMapping("/board")
    public BoardVo modifyBoard(@RequestBody BoardVo boardVo){
        Board board = boardService.setBoard(new Board(boardVo.getId(), boardVo.getTitle(), boardVo.getContent(), boardUserService.getUserByName(boardVo.getUserName())));
        return new BoardVo(board.getId(), board.getTitle(), board.getContent(), board.getBoardUser().getUsername());
    }
    
}
