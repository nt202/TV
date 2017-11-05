package ru.nt202.lab1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lab1");
        initRootLayout();

////        primaryStage.setTitle("Hello World");
////        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getClass().getResource("/lab1/MainWindow.fxml"));
//            rootLayout = loader.load();

            Parent root = FXMLLoader.load(getClass().getResource("/lab1/MainWindow.fxml"));
//            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
//        double Тнак = 5;
//        double τ = 5;
//        System.out.println(τ);
    }
}
