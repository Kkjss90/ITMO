package places;
import utility.Location;
import utility.Positional;

import java.util.Objects;

public class Rocket implements Positional {
    private String name;
    private Location TYPE = Location.INSIDE;
    public Rocket(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location where() {
        return TYPE;
    }

    @Override
    public void changeLocation(Location l) {
        if (l !=null){
            TYPE = l;
        }
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Rocket rocket = (Rocket) obj;

        return getName().equals(rocket.getName()) && TYPE.equals(rocket.TYPE);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Место '" + getName() + "'";
    }
}
