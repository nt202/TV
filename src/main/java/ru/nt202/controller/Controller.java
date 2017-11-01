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
    private LineChart<?, ?> BlackBody3200;

    @FXML
    private LineChart<?, ?> IRFilter;

    @FXML
    private LineChart<?, ?> Sensor;

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

        String filename3200 = CSVReader.class.getResource("/3200.csv").getPath();
        CSVReader csvReader2 = new CSVReader();
        csvReader2.loadFile(filename3200);
        XYChart.Series series2 = new XYChart.Series();
        for (int i = 0; i < csvReader2.getLength(); i++) {
            String xAxis = String.valueOf(csvReader2.getX().get(i));
            Double yAxis = csvReader2.getY().get(i);
            series2.getData().add(new XYChart.Data(xAxis, yAxis));
        }


        String filenameIRFilter = CSVReader.class.getResource("/BG40.csv").getPath();
        CSVReader csvReader3 = new CSVReader();
        csvReader3.loadFile(filenameIRFilter);
        XYChart.Series series3 = new XYChart.Series();
        for (int i = 0; i < csvReader3.getLength(); i++) {
            String xAxis = String.valueOf(csvReader3.getX().get(i));
            Double yAxis = csvReader3.getY().get(i);
            series3.getData().add(new XYChart.Data(xAxis, yAxis));
        }

        String filenameSensor = CSVReader.class.getResource("/IMX265LQR_GREEN.csv").getPath();
        CSVReader csvReader4 = new CSVReader();
        csvReader4.loadFile(filenameSensor);
        XYChart.Series series4 = new XYChart.Series();
        for (int i = 0; i < csvReader4.getLength(); i++) {
            String xAxis = String.valueOf(csvReader4.getX().get(i));
            Double yAxis = csvReader4.getY().get(i);
            series4.getData().add(new XYChart.Data(xAxis, yAxis));
        }

        HumanVision.getData().addAll(series1);
        HumanVision.setStyle("CHART_COLOR_1: #B57BFF;");

        BlackBody3200.getData().addAll(series2);
        BlackBody3200.setStyle("CHART_COLOR_1: #000000;");

        IRFilter.getData().addAll(series3);
        IRFilter.setStyle("CHART_COLOR_1: #FF0000;");

        Sensor.getData().addAll(series3);
        Sensor.setStyle("CHART_COLOR_1: #00FF00;");

//        HumanVision.setStyle("CHART_COLOR_2: #282725;");

//        HumanVision.getStylesheets().add("/ChartLineStyle.css");
    }

}
