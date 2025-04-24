package com.aloha.Controller;

import java.io.IOException;

import com.aloha.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    void toList(ActionEvent event) throws IOException {
        Main.setRoot("UI/List");
    }

}
