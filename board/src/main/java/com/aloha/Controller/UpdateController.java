package com.aloha.Controller;

import java.io.IOException;

import com.aloha.Main;
import com.aloha.DTO.Board;
import com.aloha.Service.BoardService;
import com.aloha.Service.BoardServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UpdateController {

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
    void update(ActionEvent event) throws IOException {    
        Board board = Board.builder()
            .no(no)
            .title(title.getText())
            .writer(writer.getText())
            .content(content.getText())
            .build();
        int result = boardService.update(board);
        if (result > 0) {
            System.out.println("게시글 수정 성공!");
            Main.setRoot("UI/List");
        } else {
            System.err.println("게시글 수정 실패!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("게시글 수정 실패");
            alert.setContentText("게시글을 수정하는 데 실패했습니다. 다시 시도해주세요.");
            alert.showAndWait();
        }

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
