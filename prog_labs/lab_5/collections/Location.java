package collections;

public class Location {
    private double x;
    private int y;
    private Float z; //Поле не может быть null
    private String name; //Поле не может быть null
    public Location(double x, int y, Float z, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}
