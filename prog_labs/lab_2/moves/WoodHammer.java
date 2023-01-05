package moves;
import ru.ifmo.se.pokemon.*;
public class WoodHammer extends PhysicalMove{
    public WoodHammer(){
        super(Type.GRASS, 120, 10);
    }
    @Override
    protected void applySelfDamage(Pokemon att, double damage){
        att.setMod(Stat.HP, (int)damage/3);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Wood Hammer";
    }
}
