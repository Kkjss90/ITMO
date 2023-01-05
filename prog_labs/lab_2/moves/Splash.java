package moves;
import ru.ifmo.se.pokemon.*;
public class Splash extends StatusMove{
    public Splash(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected java.lang.String describe(){
        return "использует Splash, который ничего не делает (странная атака)";
    }
}
