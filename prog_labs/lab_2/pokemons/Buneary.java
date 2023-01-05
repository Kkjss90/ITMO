package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;
public class Buneary extends Pokemon {
    public Buneary (String name, int level){
        super(name, level);
        setStats(55, 66, 44, 44, 56, 85);
        setType(Type.NORMAL);
        setMove(new Charm(), new Thunderbolt(), new Splash());
    }
}
