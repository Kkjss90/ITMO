package moves;
import ru.ifmo.se.pokemon.*;
public class Absorb extends SpecialMove{
    public Absorb (){
        super(Type.GRASS, 20, 100);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.HP, -2);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Absorb";
    }
}
