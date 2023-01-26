package characters;
import objects.Bed;
import objects.RockingChair;
import objects.Spacesuit;
import places.Outside;
import places.Rocket;
import utility.Human;
import utility.NeznaykaIsNotDressedException;

import java.util.Objects;


public class Znayka extends Human {
    private Object place;
    public Znayka(){
        super("Знайка");
    }

    public String comand(Spacesuit spacesuit){
        return " " + toString() + " отдал команду надеть всем " + spacesuit.getName() + "ы.";
    }

    @Override
    public void jump() {
        System.out.println(getName()+" прыгает");
    }
    @Override
    public void walkBy(Object obj) {
        this.place = obj;
        if (obj instanceof Outside) {
            System.out.println(getName() + " ковыляет по прекрасному месту: " + ((Outside)obj).getName());
        } else if (obj instanceof Rocket) {
            System.out.println(getName() + " ковыляет по прекрасному месту: " + ((Rocket)obj).getName());
        } else {
            System.out.println(getName() + " ковыляет где-то неизвестно где");
        }
    }

    @Override
    public void stopWalking(Object obj) {
        this.place = obj;
        if (obj instanceof Outside) {
            System.out.println(getName() + " нагулялся по прекрасному месту: " + ((Outside)obj).getName());
        } else if (obj instanceof Rocket) {
            System.out.println(getName() + " нагулялся по прекрасному месту: " + ((Rocket)obj).getName());
        } else {
            System.out.println(getName() + " нагулялся где-то неизвестно где");
        }
    }
    @Override
    public void carry(Object obj) {
        if (obj!=null) {
            if (obj instanceof RockingChair) {
                System.out.println(getName() + " тащит " + ((RockingChair) obj).getName());
            } else if (obj instanceof Bed) {
                System.out.println("Как "+getName()+" может тащить кровать? Конечно ничего не тащит");
            } else if (obj instanceof Spacesuit) {
                System.out.println(getName() + " тащит " + ((Spacesuit) obj).getName());
            }else if (obj instanceof Neznayka){
                System.out.println(getName() + " тащит " + ((Neznayka) obj).getName());
            }
        }else {
            System.out.println("Нечего тащить. Конечно"+getName()+" ничего не тащит");
        }
    }
    @Override
    public void dressOff(Spacesuit spacesuit, Neznayka neznayka) throws NeznaykaIsNotDressedException {
        if (spacesuit!=null & neznayka!=null){
            System.out.println(getName()+" пытается снять "+spacesuit.getName()+" c "+neznayka.getName());
        }
        else {
            throw new NeznaykaIsNotDressedException(getName() + " не снимает ничего ни с себя, ни с кого-то другого");
        }
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Znayka znayka = (Znayka) obj;

        return getName().equals(znayka.getName()) && place.equals(znayka.place);
    }
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(place);
    }

    @Override
    public String toString() {
        if (place != null) {
            if (place instanceof Outside ){
                return "Персонаж '" + getName() + "', находится в: '" + ((Outside)place).getName() + "'";
            } else if (place instanceof Rocket) {
                return "Персонаж '" + getName() + "', находится в: '" + ((Rocket)place).getName() + "'";
            }
        }
        return "Персонаж '" + getName() + "'";
    }
}
