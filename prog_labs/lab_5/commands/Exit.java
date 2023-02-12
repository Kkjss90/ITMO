package commands;

public class Exit implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
