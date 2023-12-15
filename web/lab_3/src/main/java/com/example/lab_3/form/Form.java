package com.example.lab_3.form;

import java.io.Serializable;

public class Form implements Serializable {
    private final int[] valueX = {-4, -3, -2, -1, 0, 1, 2, 3, 4};
    private final boolean[] booleanX = new boolean[9];

    private double InputY;
    private double InputR;

    public int[] getValueX() {
        return valueX;
    }

    public boolean[] getBooleanX() {
        return booleanX;
    }

    public double getInputY() {
        return InputY;
    }

    public void setInputY(double inputY) {
        InputY = inputY;
    }

    public double getInputR() {
        return InputR;
    }

    public void setInputR(double inputR) {
        InputR = inputR;
    }
}
