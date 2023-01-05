package objects;

import utility.Movable;

public class Bed implements Movable {
    private final String name;
    public Bed(){
        name = "Койка";
    }
    public Bed(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void move() {
        System.out.println(getName()+" подвинуто");
    }
}
