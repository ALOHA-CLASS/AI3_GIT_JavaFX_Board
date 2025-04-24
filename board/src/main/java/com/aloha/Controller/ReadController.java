package com.aloha.Controller;

import java.io.IOException;

import com.aloha.Main;
import com.aloha.DTO.Board;
import com.aloha.Service.BoardService;
import com.aloha.Service.BoardServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReadController {

    @FXML
    private Button btnList;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextArea content;

    @FXML
    private TextField title;

    @FXML
    private TextField writer;

    int no;
    BoardService boardService;
    Board board;
    
    @FXML
    void initialize() {
        boardService = new BoardServiceImpl();
    }
    
    @FXML
    void toList(ActionEvent event) throws IOException {
        Main.setRoot("UI/List");
    }
    
    @FXML
    void toUpdate(ActionEvent event) throws IOException {
        String fxml = "UI/Update";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        UpdateController updateController = (UpdateController) fxmlLoader.getController();
        updateController.passData(no);
        Main.setRoot(root);
    }
    
    /**
     * ⭐ 데이터 전달 받기
     * 게시글 번호를 전달받아 게시글 정보를 조회하여 화면에 표시
     * @param boardNo
     */
    public void passData(int boardNo) {
        no = boardNo;
        board = boardService.select(no);
        title.setText(board.getTitle());
        writer.setText(board.getWriter());
        content.setText(board.getContent());
    }

}
