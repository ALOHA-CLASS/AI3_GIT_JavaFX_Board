module com.aloha {
    requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires lombok;
	
	opens com.aloha to javafx.base, javafx.graphics, javafx.fxml;
	opens com.aloha.Controller to javafx.fxml;
	opens com.aloha.DTO to javafx.base;

    exports com.aloha;
}
