package moves;
import ru.ifmo.se.pokemon.*;
public class GrassWhistle extends StatusMove {
    public GrassWhistle (){
        super(Type.GRASS, 0, 55);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        Effect slp = new Effect().turns((int)((Math.random()+1)*1)).condition(Status.SLEEP);
        p.addEffect(slp);
    }
    @Override
    protected java.lang.String describe(){
        return "использует Grass Whistle";
    }
}
