package ru.nt202.lab1.model;

import java.io.*;
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

    public void loadFile(final String path) {
        BufferedReader br = null;
        try {
            String str;
            br = new BufferedReader(new FileReader(path));
            int i = 0;
            while ((str = br.readLine()) != null) {
                String[] point = str.split(
                        ",");
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


    public void loadFile(final InputStream path) {
        BufferedReader br = null;
        try {
            String str;
            br = new BufferedReader(new InputStreamReader(path));
            int i = 0;
            while ((str = br.readLine()) != null) {
                String[] point = str.split(
                        ",");
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
}
