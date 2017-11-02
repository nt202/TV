package ru.nt202.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import ru.nt202.model.CSVReader;
import ru.nt202.model.Content;
import ru.nt202.model.ContentBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Content content;

    private ArrayList<Integer> Lambda;

    private String filenameHumanVision = CSVReader.class.getResource("/HumanVision.csv").getPath();
    private String filename3200 = CSVReader.class.getResource("/3200.csv").getPath();
    private String filenameIRFilter = CSVReader.class.getResource("/BG40.csv").getPath();
    private String filenameSensor = CSVReader.class.getResource("/IMX265LQR_GREEN.csv").getPath();

    @FXML
    private TextField snr;

    @FXML
    private TextField noise;

    @FXML
    private TextField ro;

    @FXML
    private TextField tau;

    @FXML
    private TextField df;

    @FXML
    private TextField K;

    @FXML
    private TextField time;

    @FXML
    private TextField dx;

    @FXML
    private TextField dy;

    @FXML
    private CheckBox x2;

    public void calculateE(ActionEvent event) {
        double ap;
        if (x2.isSelected()) {
            ap = 2 * Double.parseDouble(dx.getCharacters().toString()) *
                    Double.parseDouble(dy.getCharacters().toString());
        } else {
            ap = Double.parseDouble(dx.getCharacters().toString()) *
                    Double.parseDouble(dy.getCharacters().toString());
        }

        content = new ContentBuilder()
                .setSnr(Double.parseDouble(snr.getCharacters().toString()))
                .setNoise(Double.parseDouble(noise.getCharacters().toString()))
                .setRo(Double.parseDouble(ro.getCharacters().toString()))
                .setTau(Double.parseDouble(tau.getCharacters().toString()))
                .setDf(Double.parseDouble(df.getCharacters().toString()))
                .setTime(Double.parseDouble(time.getCharacters().toString()))
                .setK(Double.parseDouble(K.getCharacters().toString()))
                .setAp(ap)
                .setNu(getArray(filenameHumanVision))
                .setF(getArray(filename3200))
                .setTauIR(getArray(filenameIRFilter))
                .setLambda(Lambda)
                .setTeta(getArray(filenameSensor))
                .createContent();

        content.calculateE();
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
    public void initialize(URL location, ResourceBundle resources) {

        HumanVision.getData().addAll(getSeries(filenameHumanVision));
        HumanVision.setStyle("CHART_COLOR_1: #B57BFF;");


        BlackBody3200.getData().addAll(getSeries(filename3200));
        BlackBody3200.setStyle("CHART_COLOR_1: #000000;");

        IRFilter.getData().addAll(getSeries(filenameIRFilter));
        IRFilter.setStyle("CHART_COLOR_1: #FF0000;");

        Sensor.getData().addAll(getSeries(filenameSensor));
        Sensor.setStyle("CHART_COLOR_1: #00FF00;");

//        HumanVision.getStylesheets().add("/ChartLineStyle.css");
    }

    private XYChart.Series getSeries(String filename) {
        CSVReader csvReader = new CSVReader();
        csvReader.loadFile(filename);
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < csvReader.getLength(); i++) {
            String xAxis = String.valueOf(csvReader.getX().get(i));
            Double yAxis = csvReader.getY().get(i);
            series.getData().add(new XYChart.Data(xAxis, yAxis));
        }
        return series;
    }

    private ArrayList<Double> getArray(String filename) {
        CSVReader csvReader = new CSVReader();
        csvReader.loadFile(filename);
        if (Lambda == null) {
            Lambda = csvReader.getX();
        }
        return csvReader.getY();
    }

}
