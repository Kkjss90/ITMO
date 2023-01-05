package moves;
import ru.ifmo.se.pokemon.*;
public class Thunderbolt extends SpecialMove{
    public Thunderbolt(){
        super(Type.ELECTRIC, 90, 100);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect par = new Effect().chance(0.1).condition(Status.PARALYZE);
        p.addEffect(par);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Thunderbolt";
    }
}
