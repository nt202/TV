package ru.nt202.model;

import java.util.ArrayList;

public class Content {
    private static double noise = 2; // количество шумовых электронов
    private static double Ap = 11; // площадь пикселя [мкм^2]
    private static double snr = 6; // отношения сигнал/шум [дБ]

    private static double ro = 0.89; // коэффициент отражения объекта
    private static double K = 1; // коэффициент передачи истокового повторителя в случае матричного ПЗС
    private static double time = 20; // [мс]

    private static double df = 0.714; // относительное отверстие объектива
    // D – входной зрачок объектива, f – фокусное расстояние

    private static double tau = 0.9; // коэффициент пропускания объектива
    // или же коэффициент усиления встроенного усилителя в КМОП-сенсоре

    private static final double h = 6.63 * Math.pow(10, -34); // постоянная Планка [Дж*с]
    private static final double k = 683; // фотометрический коэффициент [лм/Вт]
    private static final double c = 3 * Math.pow(10, 8); // скорость света
    private static final double d = 5 * Math.pow(10, -9);

    private static ArrayList<Double> F;
    private static ArrayList<Double> Nu;
    private static ArrayList<Double> TauIR;
    private static ArrayList<Double> Teta;
    private static ArrayList<Integer> Lambda;

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

    public double calculateE() {
        double I;

        double E = (4 * Math.pow(10, snr / 20) * noise * h * c * k) /
                (ro * tau * Math.pow(df, 2) * K * Ap * Math.pow(10, -12) * time * Math.pow(10, -3));

        double nominator = 0;
        double denominator = 0;

        for (int i = 0; i < Lambda.size(); i++) {
            nominator += d * F.get(i) * Nu.get(i) * TauIR.get(i);
            denominator += d * Lambda.get(i) * Math.pow(10, -9) * F.get(i) * TauIR.get(i) * Teta.get(i);
        }

        I = nominator / denominator;

        E = E * I;
        return E;
    }

    public static double calculateFi(double e) {
        double res = (e * ro * tau * Math.pow(df, 2) * K * Ap * Math.pow(10, -12) * time * Math.pow(10, -3)) /
                (4 * noise * h * c * k);
        double nominator = 0;
        double denominator = 0;
        for (int i = 0; i < Lambda.size(); i++) {
            nominator += d * Lambda.get(i) * Math.pow(10, -9) * F.get(i) * TauIR.get(i) * Teta.get(i);
            denominator += d * F.get(i) * Nu.get(i) * TauIR.get(i);
        }
        return 20*Math.log10(res*nominator/denominator);
    }
}
