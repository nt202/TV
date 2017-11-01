package ru.nt202.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVReader {
    public ArrayList<Integer> getX() {
        return x;
    }

    public ArrayList<Double> getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    private int length;
    private ArrayList<Integer> x = new ArrayList<Integer>();
    private ArrayList<Double> y = new ArrayList<Double>();

    public void loadFile(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String str;
            int i = 0;
            while ((str = br.readLine()) != null) {
                String[] point = str.split(",");
                x.add(i, Integer.parseInt(point[0]));
                y.add(i, Double.parseDouble(point[1]));
//                System.out.println(x.get(i) + " " + y.get(i));
                i++;
            }
            length = i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                // NOPE
            }
        }
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        csvReader.loadFile("osjfg");
    }
}
