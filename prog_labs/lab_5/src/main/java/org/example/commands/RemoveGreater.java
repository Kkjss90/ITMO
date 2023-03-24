package org.example.commands;

public class RemoveGreater implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, превышающие заданный";
    }

    @Override
    public String getName() {
        return "remove_greater";
    }
}
