package commands;

public class FilterByDistance implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля distance которых равно заданному";
    }

    @Override
    public String getName() {
        return "filter_by_distance";
    }
}
