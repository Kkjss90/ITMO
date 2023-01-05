package moves;
import ru.ifmo.se.pokemon.*;
public class DoubleHit extends PhysicalMove{
    public DoubleHit(){
        super(Type.NORMAL, 35, 90, 0, 2);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Double Hit";
    }
}
