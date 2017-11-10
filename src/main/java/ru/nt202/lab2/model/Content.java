package ru.nt202.lab2.model;

import java.util.ArrayList;

public class Content {
//    private double snr = 3;
//    private double photodiodCapacity = 10000;

    int length = 601;

    private double[] sensor = new double[601];
    private double[] object = new double[601];
    private double[] background = new double[601];

    private double[] nS = new double[601];
    private double[] nB = new double[601];

    private double c1 = 1, c2 = 1;

    private double stepNm = 5;
    private double step = stepNm * Math.pow(10, -9);
    private double variance = 10;

    public void setSensor(ArrayList<Double> list) {
        if (list.size() == length) {
            for (int i = 0; i < length; i++) {
                sensor[i] = list.get(i);
            }
        }
    }

    public void setObject(ArrayList<Double> list) {
        if (list.size() == length) {
            for (int i = 0; i < length; i++) {
                object[i] = list.get(i);
            }
        }
    }

    public void setBackground(ArrayList<Double> list) {
        if (list.size() == length) {
            for (int i = 0; i < length; i++) {
                background[i] = list.get(i);
            }
        }
    }

    public void calculateNSAndNB() {
        for (int i = 0; i < length; i++) {
            nS[i] = c1 * sensor[i] * object[i];
            nB[i] = c1 * sensor[i] * background[i];
        }
    }

    public Result getMaxSNR(int lambdaStart, int lambdaFinish) {
        double max = 0;
        int lambdaMin = 0;
        int lambdaMax = 0;
        for (int i = (int) (lambdaStart / stepNm) + 1; i < (int) (lambdaFinish / stepNm) - 1; i++) {
            for (int j = i + 1; j < (int) (lambdaFinish / stepNm); j++) {
                double snr = calculateSNR(i, j);
                if (snr > max) {
                    max = snr;
                    lambdaMin = i;
                    lambdaMax = j;
                }
            }
        }
        return new Result<>(max, lambdaMin, lambdaMax);
    }

    public Result getMaxContrast(int lambdaStart, int lambdaFinish) {
        double max = 0;
        int lambdaMin = 0;
        int lambdaMax = 0;
        for (int i = (int) (lambdaStart / stepNm) + 1; i < (int) (lambdaFinish / stepNm) - 1; i++) {
            for (int j = i + 1; j < (int) (lambdaFinish / stepNm); j++) {
                double snr = calculateContrast(i, j);
                if (snr > max) {
                    max = snr;
                    lambdaMin = i;
                    lambdaMax = j;
                }
            }
        }
        return new Result<>(max, lambdaMin, lambdaMax);
    }

    public Result getMaxMultiplicationContrastAndSNR(int lambdaStart, int lambdaFinish) {
        double max = 0;
        int lambdaMin = 0;
        int lambdaMax = 0;
        for (int i = (int) (lambdaStart / stepNm) + 1; i < (int) (lambdaFinish / stepNm) - 1; i++) {
            for (int j = i + 1; j < (int) (lambdaFinish / stepNm); j++) {
                double snr = calculateMultiplicationContrastAndSNR(i, j);
                if (snr > max) {
                    max = snr;
                    lambdaMin = i;
                    lambdaMax = j;
                }
            }
        }
        return new Result<>(max, lambdaMin, lambdaMax);
    }


    private double calculateSNR(int iStart, int iFinish) {
        double snr;
        double nominator = 0;
        double denominator = 0;
        for (int i = iStart; i < iFinish; i++) {
            nominator += nS[iStart] * step;
            denominator += Math.sqrt(nS[iStart] * step + nB[iStart] * step + variance);
        }
        snr = nominator / denominator;
        return snr;
    }

    private double calculateContrast(int iStart, int iFinish) {
        double contrast;
        double nominator = 0;
        double denominator = 0;
        for (int i = iStart; i < iFinish; i++) {
            nominator += nS[iStart] * step;
            denominator += nB[iStart] * step;
        }
        contrast = nominator / denominator;
        return contrast;
    }

    private double calculateMultiplicationContrastAndSNR(int iStart, int iFinish) {
        double multiplication;
        double nominator = 0;
        double denominator = 0;
        for (int i = iStart; i < iFinish; i++) {
            nominator += nS[iStart] * step;
            denominator += nB[iStart] * step *
                    Math.sqrt(nS[iStart] * step + nB[iStart] * step + variance);
        }
        nominator = Math.pow(nominator, 2);
        multiplication = nominator / denominator;
        return multiplication;
    }

    public static class Result<Value, LStart, LFinish> {
        public Value value;
        public LStart lStart;
        public LFinish lFinish;

        public Result(Value value, LStart lStart, LFinish lFinish) {
            this.value = value;
            this.lStart = lStart;
            this.lFinish = lFinish;
        }
    }
}
