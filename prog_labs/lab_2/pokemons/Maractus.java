package pokemons;
import moves.*;
import ru.ifmo.se.pokemon.*;
public class Maractus extends Pokemon{
    public Maractus (String name, int level) {
        super(name, level);
        setStats(75, 86, 67, 106, 67, 60);
        setType(Type.GRASS);
        setMove(new CottonGuard(), new GrassWhistle(), new  WoodHammer(), new Absorb());
    }
}