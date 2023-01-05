package objects;

import utility.Movable;

public class Sun implements Movable{
    private final String name;
    public Sun(){
        name = "Солнышко";
    }
    public Sun(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void move() {
        System.out.println(getName()+" движется");
    }
}