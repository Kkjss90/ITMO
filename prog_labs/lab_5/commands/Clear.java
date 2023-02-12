package commands;

public class Clear implements Command {

    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
