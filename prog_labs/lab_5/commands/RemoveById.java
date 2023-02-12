package commands;

public class RemoveById implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}
