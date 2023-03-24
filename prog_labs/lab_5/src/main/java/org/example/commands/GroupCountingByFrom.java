package org.example.commands;

public class GroupCountingByFrom implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "сгруппировать элементы коллекции по значению поля from, вывести количество элементов в каждой группе";
    }

    @Override
    public String getName() {
        return "group_counting_by_from";
    }
}
