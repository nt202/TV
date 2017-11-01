package ru.nt202.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import ru.nt202.model.CSVReader;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public void pressButton(ActionEvent event) {
        System.out.println("pressed");
    }

    @FXML
    private LineChart<?, ?> HumanVision;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        String filenameHumanVision = CSVReader.class.getResource("/HumanVision.csv").getPath();
        CSVReader csvReader1 = new CSVReader();
        csvReader1.loadFile(filenameHumanVision);
        XYChart.Series series1 = new XYChart.Series();
        for (int i = 0; i < csvReader1.getLength(); i++) {
            String xAxis = String.valueOf(csvReader1.getX().get(i));
            Double yAxis = csvReader1.getY().get(i);
            series1.getData().add(new XYChart.Data(xAxis, yAxis));
        }

//        String filename3200 = CSVReader.class.getResource("/3200.csv").getPath();
        String filename3200 = CSVReader.class.getResource("/IMX265LQR_BLUE.csv").getPath();
        CSVReader csvReader2 = new CSVReader();
        csvReader2.loadFile(filename3200);
        XYChart.Series series2 = new XYChart.Series();
        for (int i = 0; i < csvReader2.getLength(); i++) {
            String xAxis = String.valueOf(csvReader2.getX().get(i));
            Double yAxis = csvReader2.getY().get(i);
            series2.getData().add(new XYChart.Data(xAxis, yAxis));
        }

        series1.setName("Кривая видимости зрительного анализатора");
        HumanVision.setStyle("CHART_COLOR_1: #FFFF00");
        series2.setName("Спектральные характеристики источника белого (3200 K)");
        HumanVision.getData().addAll(series1, series2);

    }

}
