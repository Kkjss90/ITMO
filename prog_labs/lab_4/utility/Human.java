package utility;

import characters.Neznayka;
import objects.Spacesuit;

import java.util.Objects;
import utility.NeznaykaIsNotDressedException;

public abstract class Human implements Movable {
    private final String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    public abstract void walkBy(Object obj);
    public abstract void stopWalking(Object obj);
    public abstract void jump();
    public abstract void carry(Object obj);
    public abstract void dressOff(Spacesuit spacesuit, Neznayka neznayka) throws NeznaykaIsNotDressedException;
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Human human = (Human) obj;

        return name.equals(human.name);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
    @Override
    public void move(){
        System.out.println("Подвинут");
    }
    @Override
    public String toString() {
        return "Персонаж, которого зовут '" + name + "'";
    }
}
