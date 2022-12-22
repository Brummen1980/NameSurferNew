package com.example.namesurfer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NSApplication extends Application {
    static Stage primaryStage;
    static Image appIcon = new Image(Objects.requireNonNull(NSApplication.class.getResourceAsStream("/logo.png")));
    static Stage getStage() {return primaryStage;}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NSApplication.class.getResource("main-view.fxml"));
        primaryStage = stage;
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.getIcons().add(appIcon);
        stage.setTitle("Name surfer");
        stage.setMinHeight(350);
        stage.setMinWidth(550);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}