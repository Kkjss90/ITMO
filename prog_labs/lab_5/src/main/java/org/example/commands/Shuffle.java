package org.example.commands;

public class Shuffle implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "перемешать элементы коллекции в случайном порядке";
    }

    @Override
    public String getName() {
        return "shuffle";
    }
}
