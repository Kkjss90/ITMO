package org.example.commands;

public class RemoveAnyByDistance implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "удалить из коллекции один элемент, значение поля distance которого эквивалентно заданному";
    }

    @Override
    public String getName() {
        return "remove_any_by_distance";
    }
}
