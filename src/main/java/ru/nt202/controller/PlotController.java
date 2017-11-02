package ru.nt202.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import ru.nt202.model.Content;

import java.net.URL;
import java.util.ResourceBundle;

public class PlotController implements Initializable {

    @FXML
    private LineChart<?, ?> SNRfromE;


    public static double eMin = 0, eMax = 0;

    public void seteMin(double eMin) {
        PlotController.eMin = eMin;
    }

    public void seteMax(double eMax) {
        PlotController.eMax = eMax;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series = new XYChart.Series();
        double step = (eMax+eMin)/100;
        for (double i = eMin; i <= eMax; i+=step) {
            String xAxis = String.valueOf(i);
            Double yAxis = Content.calculateFi(i);
            series.getData().add(new XYChart.Data(xAxis, yAxis));
        }
        SNRfromE.getData().addAll(series);
    }
}
