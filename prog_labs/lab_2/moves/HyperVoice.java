package moves;
import ru.ifmo.se.pokemon.*;
public class HyperVoice extends SpecialMove{
    public HyperVoice(){
        super(Type.NORMAL, 90, 100);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Hyper Voice";
    }
}
