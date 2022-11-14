package com.hitec.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.hitec.board.argument.AuthUser;
import com.hitec.board.model.Board;
import com.hitec.board.model.BoardUser;
import com.hitec.board.service.BoardService;
import com.hitec.board.service.BoardUserService;
import com.hitec.board.vo.AuthUserVo;
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
    public BoardVo setBoard(@AuthUser AuthUserVo authUser, @RequestBody BoardVo boardVo){

        BoardUser boardUser = null;
        if(boardUserService.userExist(authUser.getId())){
            boardUser = boardUserService.getUser(authUser.getId());
        }else{
            boardUser = new BoardUser(authUser.getId(), authUser.getUsername(), authUser.getName(), authUser.getPreferred_username(), authUser.getEmail());
            boardUser = boardUserService.setUser(boardUser);
        }

        Board board = boardService.setBoard(new Board(null, boardVo.getTitle(), boardVo.getContent(), boardUser));
        return new BoardVo(board.getId(), board.getTitle(), board.getContent(), board.getBoardUser().getUsername());
    }

    @PutMapping("/board")
    public BoardVo modifyBoard(@AuthUser AuthUserVo authUser, @RequestBody BoardVo boardVo){
        Board board = boardService.setBoard(new Board(boardVo.getId(), boardVo.getTitle(), boardVo.getContent(), boardUserService.getUserByName(boardVo.getUserName())));
        return new BoardVo(board.getId(), board.getTitle(), board.getContent(), board.getBoardUser().getUsername());
    }
    
}
