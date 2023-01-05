package moves;
import ru.ifmo.se.pokemon.*;
public class FocusBlast extends SpecialMove{
    public FocusBlast(){
        super(Type.FIGHTING, 120, 70);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect sd_minimize = new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE,-1);
        p.addEffect(sd_minimize);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Focus Blast";
    }
}
