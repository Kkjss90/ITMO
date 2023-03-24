package collections;

/**
 * Позиция - класс, который содержит координаты элемента коллекции
 */
public class Position {
    private Float x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private Double z; //Поле не может быть null
    public Position(Float x, Float y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }
    public void setX(Float x){
        this.x = x;
    }
    public void setY(Float y){
        this.y = y;
    }
    public void setZ(Double z){
        this.z = z;
    }
}
