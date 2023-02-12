package commands;

public class ExecuteScript implements Command {
    @Override
    public void execute() {

    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
