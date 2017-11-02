package ru.nt202.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.nt202.Main;
import ru.nt202.model.CSVReader;
import ru.nt202.model.Content;
import ru.nt202.model.ContentBuilder;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Content content;

    private ArrayList<Double> Nu;
    private ArrayList<Double> F;
    private ArrayList<Double> TauIR;
    private ArrayList<Double> Teta;
    private ArrayList<Integer> Lambda;

    private String filenameHumanVision = CSVReader.class.getResource("/HumanVision/HumanVision.csv").getPath();
    private String filename3200 = CSVReader.class.getResource("/3200/3200.csv").getPath();
    private String filenameIRFilter = CSVReader.class.getResource("/Filters/BG40.csv").getPath();
    private String filenameSensor = CSVReader.class.getResource("/Sensors/IMX265LQR_GREEN.csv").getPath();

    public void openHumanVision() {
        try {
            File file = openFile();
            HumanVision.getData().clear();
            HumanVision.getData().addAll(getSeries(file));
            HumanVision.setStyle("CHART_COLOR_1: #B57BFF;");
            Nu = getArray(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open3200() {
        try {
            File file = openFile();
            BlackBody3200.getData().clear();
            BlackBody3200.getData().addAll(getSeries(file));
            BlackBody3200.setStyle("CHART_COLOR_1: #000000;");
            F = getArray(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openIRFilter() {
        try {
            File file = openFile();
            IRFilter.getData().clear();
            IRFilter.getData().addAll(getSeries(file));
            IRFilter.setStyle("CHART_COLOR_1: #FF0000;");
            TauIR = getArray(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openSensor() {
        try {
            File file = openFile();
            Sensor.getData().clear();
            Sensor.getData().addAll(getSeries(file));
            Sensor.setStyle("CHART_COLOR_1: #00FF00;");
            Teta = getArray(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPlot(ActionEvent event) {
        try {
            PlotController pc = new PlotController();
            double eMin = Double.parseDouble(Emin.getCharacters().toString());
            double eMax = Double.parseDouble(Emax.getCharacters().toString());
            if (eMin < eMax) {
                pc.seteMin(eMin);
                pc.seteMax(eMax);
                try {
                    Parent root = FXMLLoader.load(PlotController.class.getClass().getResource("/PlotWindow.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Зависимость отношения сигнал/шум от освещенности на объекте");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private File openFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(Main.primaryStage);//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            return file;
        } else {
            throw new Exception();
        }
    }

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

    @FXML
    private TextField resultE;

    @FXML
    private TextField Emin;

    @FXML
    private TextField Emax;


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
                .setNu(Nu)
                .setF(F)
                .setTauIR(TauIR)
                .setLambda(Lambda)
                .setTeta(Teta)
                .createContent();
        resultE.setText(String.valueOf(content.calculateE()));
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

        HumanVision.getData().addAll(getSeries(new File(filenameHumanVision)));
        HumanVision.setStyle("CHART_COLOR_1: #B57BFF;");
        Nu = getArray(new File(filenameHumanVision));


        BlackBody3200.getData().addAll(getSeries(new File(filename3200)));
        BlackBody3200.setStyle("CHART_COLOR_1: #000000;");
        F = getArray(new File(filename3200));

        IRFilter.getData().addAll(getSeries(new File(filenameIRFilter)));
        IRFilter.setStyle("CHART_COLOR_1: #FF0000;");
        TauIR = getArray(new File(filenameIRFilter));

        Sensor.getData().addAll(getSeries(new File(filenameSensor)));
        Sensor.setStyle("CHART_COLOR_1: #00FF00;");
        Teta = getArray(new File(filenameSensor));

//        HumanVision.getStylesheets().add("/ChartLineStyle.css");
    }

    private XYChart.Series getSeries(File file) {
        CSVReader csvReader = new CSVReader();
        csvReader.loadFile(file.getAbsolutePath());
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < csvReader.getLength(); i++) {
            String xAxis = String.valueOf(csvReader.getX().get(i));
            Double yAxis = csvReader.getY().get(i);
            series.getData().add(new XYChart.Data(xAxis, yAxis));
        }
        return series;
    }

    private ArrayList<Double> getArray(File file) {
        CSVReader csvReader = new CSVReader();
        csvReader.loadFile(file.getAbsolutePath());
        if (Lambda == null) {
            Lambda = csvReader.getX();
        }
        return csvReader.getY();
    }

}
