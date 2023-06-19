package org.example.data;


import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1l;
    private int x;
    private Float y; //Значение поля должно быть больше -443, Поле не может быть null

    public Coordinates(int x, Float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + (int) x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && y.equals(coordinatesObj.getY());
        }
        return false;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y ;
    }
}
