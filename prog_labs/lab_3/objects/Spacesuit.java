package objects;

import utility.Movable;

public class Spacesuit implements Movable{
    private final String name;
    public Spacesuit(){
        name = "Скафандр";
    }
    public Spacesuit(String name){
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
