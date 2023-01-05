package places;

import utility.Location;
import utility.Positional;

import java.util.Objects;

public class Outside implements Positional {
    private String name;
    private Location TYPE = Location.OUTSIDE;
    public Outside(String name){
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
        Outside outside = (Outside) obj;

        return getName().equals(outside.getName()) && TYPE.equals(outside.TYPE);
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
