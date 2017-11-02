package ru.nt202.model;

import java.util.ArrayList;

public class Content {
    private double noise = 2; // количество шумовых электронов
    private double Ap = 11; // площадь пикселя [мкм^2]
    private double snr = 6; // отношения сигнал/шум [дБ]

    private double ro = 0.89; // коэффициент отражения объекта
    private double K = 1; // коэффициент передачи истокового повторителя в случае матричного ПЗС
    private double time = 20; // [мс]

    private double df = 0.714; // относительное отверстие объектива
    // D – входной зрачок объектива, f – фокусное расстояние

    private double tau = 0.9; // коэффициент пропускания объектива
    // или же коэффициент усиления встроенного усилителя в КМОП-сенсоре

    private final double h = 6.63 * Math.pow(10, -34); // постоянная Планка [Дж*с]
    private final double k = 683; // фотометрический коэффициент [лм/Вт]
    private final double c = 3 * Math.pow(10, 8); // скорость света

    private ArrayList<Double> F;
    private ArrayList<Double> Nu;
    private ArrayList<Double> TauIR;
    private ArrayList<Double> Teta;
    private ArrayList<Integer> Lambda;

    double I;

    public Content(double noise, double Ap, double snr, double ro, double K, double time, double df, double tau,
                   ArrayList<Double> F, ArrayList<Double> Nu, ArrayList<Double> TauIR, ArrayList<Double> Teta, ArrayList<Integer> Lambda) {
        this.noise = noise;
        this.Ap = Ap;
        this.snr = snr;
        this.ro = ro;
        this.K = K;
        this.time = time;
        this.df = df;
        this.tau = tau;
        this.F = F;
        this.Nu = Nu;
        this.TauIR = TauIR;
        this.Teta = Teta;
        this.Lambda = Lambda;
    }

    public void calculateE() {

        double E = (4 * 10 * Math.log10(snr) * noise * h * c * k) /
                (ro * tau * Math.pow(df, 2) * K * Ap * time);

//        int d = 5;
//        int sum1 = 0;
//
//        for (int i = 0; i < n; i++) {
//            sum1 += d*F[i];
//        }
        for (int i = 0; i < F.size(); i++) {
            System.out.println(F.get(i));
        }

    }
}
