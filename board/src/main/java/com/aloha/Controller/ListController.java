package com.aloha.Controller;

import java.io.IOException;
import java.util.List;

import com.aloha.Main;
import com.aloha.DTO.Board;
import com.aloha.Service.BoardService;
import com.aloha.Service.BoardServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ListController {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnInsert;

    @FXML
    private TableColumn<?, ?> colCreatedAt;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colUpdatedAt;

    @FXML
    private TableColumn<?, ?> colWriter;
    
    @FXML
    private TableView<Board> tableView;

    List<Board> boardList = null;

    BoardService boardService;
    
    @FXML
    void initialize() {
    	boardService = new BoardServiceImpl();
    	boardList = boardService.list();
    	
    	
    	// * TableColumn 에 Board 객체의 속성 매핑하기
    	colNo.setCellValueFactory( new PropertyValueFactory<>("No") );
    	colTitle.setCellValueFactory( new PropertyValueFactory<>("Title") );
    	colWriter.setCellValueFactory( new PropertyValueFactory<>("Writer") );
    	// colCreatedAt.setCellValueFactory( new PropertyValueFactory<>("CreatedAt") );
    	// colUpdatedAt.setCellValueFactory( new PropertyValueFactory<>("UpdatedAt") );
    	colCreatedAt.setCellValueFactory( new PropertyValueFactory<>("CreatedAtFmt") );
    	colUpdatedAt.setCellValueFactory( new PropertyValueFactory<>("UpdatedAtFmt") );
    	// (코드 설명)
    	// * new PropertyValueFactory("게터이름")		: 값으로 들어갈 객체의 게터 이름을 지정
    	// ex) new PropertyValueFactory("Title") --> getTitle() 게터 메소드의 get을 제외한 "Title"과 일치
    	// * setCellValueFactory()					: 셀의 값을 지정하는 메소드
    	// ** module-info.java 에 javafx.base 모듈 추가해야 사용 가능
    
    	// 테이블뷰에 데이터 추가하기
    	ObservableList<Board> list = FXCollections.observableArrayList(boardList);
    	tableView.setItems(list);


        tableView.setOnMouseClicked( new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				// double clicked
				if( event.getClickCount() == 2 && tableView.getSelectionModel().getSelectedItem() != null ) {
					int boardNo = tableView.getSelectionModel().getSelectedItem().getNo();
					
					try {
						String fxml = "UI/Read";
						FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
						Parent root = fxmlLoader.load();
						ReadController readController = (ReadController) fxmlLoader.getController();
						readController.passData(boardNo);
						Main.setRoot(root);
					} catch (Exception e) {
						System.err.println("List -> Read Error...");
                        e.printStackTrace();
					}
				}
			}
    		
		});
    }

    @FXML
    void toExit(ActionEvent event) {
        Main.exit();
    }

    @FXML
    void toInsert(ActionEvent event) throws IOException {
        Main.setRoot("UI/Insert");
    }

}
