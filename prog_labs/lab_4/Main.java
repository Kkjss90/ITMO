import characters.*;
import objects.*;
import places.*;
import utility.*;

public class Main {
    class End{
         private static void done(){
        System.out.println("Так они и сделали.");
    }
    }
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
        Sun sun = new Sun();

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
        try{
            pilulkin.dressOff(spacesuit, neznayka);
        }catch (NeznaykaIsNotDressedException e){
            System.out.println(e.getMessage());
        }
        try{
            fuksiya.dressOff(spacesuit, neznayka);
        }catch (NeznaykaIsNotDressedException e) {
            System.out.println(e.getMessage());
        }
        try {
            znayka.dressOff(spacesuit, neznayka);
        }catch (NeznaykaIsNotDressedException e){
            System.out.println(e.getMessage());
        }
        try{
            seledochka.dressOff(spacesuit, neznayka);
        }catch (NeznaykaIsNotDressedException e){
            System.out.println(e.getMessage());
        }
        neznayka.feelBetter(spacesuit);
        neznayka.walkBy(rocket);
        neznayka.shakeArm();
        neznayka.shakeLeg();
        neznayka.rememberSun(sun);
        System.out.println(neznayka.feelBad()+neznayka.and()+neznayka.fallDown());
        pilulkin.takeCareOfNeznayka(neznayka);
        System.out.println(neznayka.getName()+neznayka.recover()+neznayka.but()+neznayka.white());
        End.done();
    }
}