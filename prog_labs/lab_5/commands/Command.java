package commands;

public interface Command {
    public abstract void execute();
    public String getDescription();
    public String getName();
}
