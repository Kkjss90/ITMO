package commands;

public class Add implements Command {
    @Override
    public void execute() {
        
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}
