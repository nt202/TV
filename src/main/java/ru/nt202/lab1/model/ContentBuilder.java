package ru.nt202.lab1.model;

import java.util.ArrayList;

public class ContentBuilder {
    private double noise;
    private double ap;
    private double snr;
    private double ro;
    private double k;
    private double time;
    private double df;
    private double tau;
    private ArrayList<Double> f;
    private ArrayList<Double> nu;
    private ArrayList<Double> tauIR;
    private ArrayList<Double> teta;
    private ArrayList<Integer> lambda;

    public ContentBuilder setNoise(double noise) {
        this.noise = noise;
        return this;
    }

    public ContentBuilder setAp(double ap) {
        this.ap = ap;
        return this;
    }

    public ContentBuilder setSnr(double snr) {
        this.snr = snr;
        return this;
    }

    public ContentBuilder setRo(double ro) {
        this.ro = ro;
        return this;
    }

    public ContentBuilder setK(double k) {
        this.k = k;
        return this;
    }

    public ContentBuilder setTime(double time) {
        this.time = time;
        return this;
    }

    public ContentBuilder setDf(double df) {
        this.df = df;
        return this;
    }

    public ContentBuilder setTau(double tau) {
        this.tau = tau;
        return this;
    }

    public ContentBuilder setF(ArrayList<Double> f) {
        this.f = f;
        return this;
    }

    public ContentBuilder setNu(ArrayList<Double> nu) {
        this.nu = nu;
        return this;
    }

    public ContentBuilder setTauIR(ArrayList<Double> tauIR) {
        this.tauIR = tauIR;
        return this;
    }

    public ContentBuilder setTeta(ArrayList<Double> teta) {
        this.teta = teta;
        return this;
    }

    public ContentBuilder setLambda(ArrayList<Integer> lambda) {
        this.lambda = lambda;
        return this;
    }

    public Content createContent() {
        return new Content(noise, ap, snr, ro, k, time, df, tau, f, nu, tauIR, teta, lambda);
    }
}