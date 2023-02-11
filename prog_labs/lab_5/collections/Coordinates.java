package collections;

public class Coordinates {
    private Float x; //Поле не может быть null
    private int y; //Значение поля должно быть больше -443
    public Coordinates(Float x, int y){
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
