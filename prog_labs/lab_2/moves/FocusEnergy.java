package moves;
import ru.ifmo.se.pokemon.*;
public class FocusEnergy extends StatusMove {
    public FocusEnergy(){
        super(Type.NORMAL, 0, 0);
    }
    protected double calcCriticalHit(Pokemon att, Pokemon def){
        return (Math.random()+1)*1d/24d;
    }
    @Override
    protected java.lang.String describe(){
        return "испольует Focus Energy";
    }
}