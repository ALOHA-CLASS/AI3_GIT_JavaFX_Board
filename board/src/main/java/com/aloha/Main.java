package com.aloha;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("UI/Main"));
        Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        scene.setRoot(loadFXML(fxml));
        // Stage 크기를 FXML 루트 노드의 크기로 맞춤
        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(root.prefWidth(-1));
        stage.setHeight(root.prefHeight(-1));
    }

    /**
	 * 새로운 화면으로 이동
	 * @param root
	 */
	public static void setRoot(Parent root) throws IOException {
		scene.setRoot(root);
        // Stage 크기를 FXML 루트 노드의 크기로 맞춤
        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(root.prefWidth(-1));
        stage.setHeight(root.prefHeight(-1));
	}

    public static Map<String, ?> getMap(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        // root
        Parent root = loader.load();
        // controller
        Object controller = loader.getController();
        // scene
        Scene scene = Main.scene;
        // Map
        Map<String, Object> map = new HashMap<>();
        map.put("controller", controller);
        map.put("scene", scene);
        map.put("root", root);
        return map;
    }

    /**
	 * FXML 화면 이름을 전달받아 
	 * loader, root, controller 를 Map 으로 반환하는 메소드
	 * @param fxml
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> get(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
		Parent root = fxmlLoader.load();
		Object controller = fxmlLoader.getController();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loader", fxmlLoader);
		map.put("root", root);
		map.put("controller", controller);
		return map;
	}

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void exit() {
        // Alert 창을 띄워 사용자에게 종료 확인
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("프로그램 종료");
        alert.setHeaderText("프로그램 종료");
        alert.setContentText("정말로 종료하시겠습니까?");

        // 사용자의 선택 확인
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        if (result != ButtonType.OK) {
            return; // 사용자가 OK를 선택하지 않으면 종료하지 않음
        }

		Stage stage = (Stage) scene.getWindow();
		stage.close();
	}

    public static void main(String[] args) {
        launch();
    }

}