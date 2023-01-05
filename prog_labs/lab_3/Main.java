import characters.*;
import objects.*;
import places.*;
public class Main {
    public static void main(String[] args) {
    Neznayka neznayka = new Neznayka();
    Fuksiya fuksiya = new Fuksiya();
    Pilulkin pilulkin = new Pilulkin();
    Seledochka seledochka = new Seledochka();
    Znayka znayka = new Znayka();
    Bed bed = new Bed();
    Spacesuit spacesuit = new Spacesuit();
    RockingChair rockingChair = new RockingChair();
    Outside outside = new Outside("Где-то снаружи");
    Rocket rocket = new Rocket("Внутри ракеты");

    System.out.println(outside.getName());
    pilulkin.walkBy(outside);
    neznayka.dress(spacesuit);
    neznayka.lie(rockingChair);
    pilulkin.carry(rockingChair);
    pilulkin.stopWalking(outside);
    System.out.println(rocket.getName());
    neznayka.walkBy(rocket);
    pilulkin.carry(neznayka);
    znayka.carry(neznayka);
    seledochka.carry(neznayka);
    fuksiya.carry(neznayka);
    System.out.println(neznayka.toString());
    neznayka.lie(bed);
    pilulkin.dressOff(spacesuit, neznayka);
    fuksiya.dressOff(spacesuit, neznayka);
    znayka.dressOff(spacesuit, neznayka);
    seledochka.dressOff(spacesuit, neznayka);
    neznayka.feelBetter(spacesuit);
    neznayka.walkBy(rocket);
    neznayka.shakeArm();
    neznayka.shakeLeg();
    }
}