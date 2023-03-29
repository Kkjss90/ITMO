package org.example.commands;

public class Save implements Command {

    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }
}
