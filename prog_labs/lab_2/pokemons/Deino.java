package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;
public class Deino extends Pokemon {
    public Deino (String name, int level){
        super(name, level);
        setStats(52, 65, 50, 45, 50, 38);
        setType(Type.DARK, Type.DRAGON);
        setMove(new FocusEnergy(), new DragonRage());
    }
}
