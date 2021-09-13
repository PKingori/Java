package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("calculator-view.fxml"));

        stage.setScene(new Scene(fxmlLoader.load(), 240, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
// ---------------------   code proceed -----------