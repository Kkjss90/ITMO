package moves;
import ru.ifmo.se.pokemon.*;
public class DragonRage extends SpecialMove{
    public DragonRage(){
        super(Type.DRAGON, 0, 100);
    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        def.setMod(Stat.HP, 40);
    }
    @Override
    protected java.lang.String describe(){
        return "наносит удар Dragon Rage";
    }
}
