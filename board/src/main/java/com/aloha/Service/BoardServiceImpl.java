package com.aloha.Service;

import com.aloha.DAO.BoardDAO;
import com.aloha.DTO.Board;

public class BoardServiceImpl implements BoardService {

    BoardDAO boardDAO = new BoardDAO();

    @Override
    public int insert(Board board) {
        return boardDAO.insert(board);
    }
    
}
