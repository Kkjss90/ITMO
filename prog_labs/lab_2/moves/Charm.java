package moves;
import ru.ifmo.se.pokemon.*;
public class Charm  extends StatusMove{
    static int h = 123;
    public static void run(){
        h++;
    }
    public Charm(){
        super(Type.FAIRY, 0, 100);
    }
    @Override
    public void applyOppEffects(Pokemon p){
       Effect low_att = new Effect().stat(Stat.ATTACK, -2);
       p.addEffect(low_att);
    }
    @Override
    protected java.lang.String describe(){
        return "использует Charm";
    }
}
