package pokemons;
import moves.*;
public class Hydreigon extends pokemons.Zweilous{
    public Hydreigon (String name, int level){
        super(name, level);
        setStats(92, 105, 90, 125, 90, 98);
        addMove(new HyperVoice());
    }
}
