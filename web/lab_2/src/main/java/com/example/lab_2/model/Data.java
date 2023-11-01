package com.example.lab_2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Data implements Serializable {
    private int x;
    private double y;
    private double r;
    private boolean res;
    private long calculationTime;
    private LocalDateTime calculatedAt;

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isRes() {
        return res;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public void setCalculationTime(long calculationTime) {
        this.calculationTime = calculationTime;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }

    public long getCalculationTime() {
        return calculationTime;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Double.compare(x, data.x) == 0 && Double.compare(y, data.y) == 0 && Double.compare(r, data.r) == 0 && res == data.res && calculationTime == data.calculationTime && Objects.equals(calculatedAt, data.calculatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, res, calculationTime, calculatedAt);
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", res=" + res +
                ", calculationTime=" + calculationTime +
                ", calculatedAt=" + calculatedAt +
                '}';
    }
}
