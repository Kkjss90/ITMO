package org.example.data;


import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 2l;

    private Double x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private int z;
    private String name; //Поле не может быть null

    public Location(Double x, Float y, int z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + (int) z + x.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Location) {
            Location locationobj = (Location) obj;
            return (x == locationobj.getX()) && y.equals(locationobj.getY()) && z == locationobj.getZ() && name == locationobj.getName();
        }
        return false;
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", имя='" + name + '\'';
    }
}
