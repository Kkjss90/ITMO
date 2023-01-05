package moves;
import ru.ifmo.se.pokemon.*;
public class CottonGuard extends StatusMove {
    public CottonGuard (){
        super(Type.GRASS, 0, 0);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        Effect raise_def = new Effect().turns(-1).stat(Stat.DEFENSE, 3);
        p.addEffect(raise_def);
    }
    @Override
    protected java.lang.String describe(){
        return "использует Cotton Guard";
    }
}
