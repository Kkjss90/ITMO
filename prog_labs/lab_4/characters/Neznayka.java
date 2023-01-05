package characters;
import objects.Bed;
import objects.RockingChair;
import objects.Spacesuit;
import places.Outside;
import places.Rocket;
import utility.Human;

import java.util.Objects;

class Eye{
    public void cry(){
        System.out.println(" ,слезы сейчас же закапали.");
    }
}
public class Neznayka extends Human {
    Eye eye = new Eye();
    private Object place;
    public Neznayka() {
        super("Незнайка");
    }

    public void shakeLeg(){
        System.out.println(getName() + " попробовал пошевелить ногой, однако слабость наступила такая, что ему трудно было пошевелить рукой или ногой");
    }
    public void shakeArm(){
        System.out.println(getName() + " попробовал пошевелить рукой, однако слабость наступила такая, что ему трудно было пошевелить рукой или ногой");
    }
    public void dress(Spacesuit spacesuit){
        System.out.println(getName()+" одет в "+spacesuit.getName());
    }
    public void lie(Object obj) {
        if (obj!=null) {
            if (obj instanceof RockingChair) {
                System.out.println(getName() + " прилег в " + ((RockingChair) obj).getName());
            } else if (obj instanceof Bed) {
                System.out.println("Как "+getName()+" прилег в "+((Bed) obj).getName());
        }else {
            System.out.println("прилег на пол");
            }
        }
    }

    public void feelBetter(Spacesuit spacesuit){
        System.out.println(getName() + " оказался без "+spacesuit.getName()+" и ему стало лучше");
    }
    public void rememberSun(Sun sun){
        System.out.println(getName() + " вспомнил про "+sun.getName()+eye.cry());
    }

    @Override
    public void walkBy(Object obj){
        this.place = obj;
        if (obj instanceof Outside) {
            System.out.println(getName() + " попробовал пройтись по прекрасному месту: " + ((Outside)obj).getName()+ ", однако не смог даже встать");
        } else if (obj instanceof Rocket) {
            System.out.println(getName() + " попробовал пройтись по прекрасному месту: " + ((Rocket)obj).getName()+ ", однако не смог даже встать");
        } else {
            System.out.println(getName() + " попробовал пройтись где-то неизвестно где");
        }
    }

    @Override
    public void stopWalking(Object obj) {
        System.out.println(getName()+" не идет, потому что даже не может всать");
    }

    @Override
    public void jump() {
        System.out.println(getName()+" хотел попробовать прыгнуть, но не смог даже встать");
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
            }
        }else {
            System.out.println("Нечего тащить. Конечно"+getName()+" ничего не тащит");
        }
    }
    @Override
    public void dressOff(Spacesuit spacesuit, Neznayka neznayka) throws NeznaykaIsNotDressedException{
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
        Neznayka neznayka = (Neznayka) obj;

        return getName().equals(neznayka.getName()) && place.equals(neznayka.place);
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
