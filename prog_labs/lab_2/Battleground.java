import pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Battleground {
    public static void main(String args[]) {
        moves.Charm.run();
        Battle battle = new Battle();
        pokemons.Buneary p1 = new pokemons.Buneary("Полукрол", 1);
        pokemons.Deino p2 = new pokemons.Deino("Полуполудракон", 1);
        pokemons.Hydreigon p3 = new pokemons.Hydreigon("Дракон", 1);
        pokemons.Lopunny p4 = new pokemons.Lopunny("Крол", 1);
        pokemons.Maractus p5 = new pokemons.Maractus("Куктус", 1);
        pokemons.Zweilous p6 = new pokemons.Zweilous("Полудракон", 1);
        battle.addAlly(p1);
        battle.addAlly(p2);
        battle.addAlly(p3);
        battle.addFoe(p4);
        battle.addFoe(p5);
        battle.addFoe(p6);
        battle.go();
    }
}
