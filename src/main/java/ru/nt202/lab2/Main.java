package ru.nt202.lab2;

// 2. Контрастная чувствительность твердотельных фотоприемников

//    Цель работы: научиться оценивать контрастную чувствительность те-
//    левизионной системы при известных спектральных характеристиках объекта
//    наблюдения и фона, изучить методы спектральной селекции объектов, изу-
//    чить закон Роуза.
//    Формируемые навыки: способность рассчитать требуемые параметры
//    спектральных фильтров, оценивать предельный контраст в заданных услови-
//    ях наблюдения, понимать методы повышения контрастной чувствительности.
//    Требуемые знания: изучение тем практических занятий, посвященных
//    изучению контрастной чувствительности, пороговой чувствительности, со-
//    ставляющих шумов твердотельных фотоприемников, знания в области стати-
//    стической радиотехники.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage mainStage;
    private BorderPane mainLayout;

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        mainStage.setTitle("Lab2");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getClass().getResource("/lab2/MainWindow.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/lab2/MainWindow.fxml"));
            mainStage.setScene(new Scene(root));
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
