package commands;

public abstract class Command implements Executable{
    private String name;
    private String description;

    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription(){
        return description;
    }
    @Override
    public String toString(){
        return name + ": " + description;
    }
    @Override
    public int hashCode(){
        return name.hashCode()+description.hashCode();
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Command com = (Command) obj;
        return name.equals(com.name) && description.equals(com.description);
    }
}
