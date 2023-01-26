package objects;

import utility.Movable;

public class RockingChair implements Movable{
    private final String name;
    public RockingChair(){
        name = "Кресло-качалка";
    }
    public RockingChair(String name) {
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

    public static class AssembledRockingChair{
        public String done(){
            return "Как только кресло было готово,";
        }
    }
}
