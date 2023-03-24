package org.example.commands;

public class UpdateId implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update_id";
    }
}
