package org.example.commands;

public class RemoveLower implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }
}
