package commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс команды, которая выводит описания всех команд, реализованных в программе.
 */
public class Help implements Command {
    /**
     * Коллекция, содержащая объекты всех доступных в программе команд без дополнительных аргументов.
     */
    private HashMap<String, Command> commandWithoutArgumentsHashMap;
    /**
     * Коллекция, содержащая объекты всех доступных в программе команд с дополнительными аргументами.
     */
    private HashMap<String, CommandWithArguments> commandWithArgumentsHashMap;

    /**
     * Конструктор класса.
     *
     * @param commandHashMap              Коллекция, содержащая объекты всех доступных в программе команд без дополнительных аргументов.
     * @param commandWithArgumentsHashMap Коллекция, содержащая объекты всех доступных в программе команд с дополнительными аргументами.
     */
    public Help(HashMap<String, Command> commandHashMap, HashMap<String, CommandWithArguments> commandWithArgumentsHashMap) {
        this.commandWithoutArgumentsHashMap = commandHashMap;
        this.commandWithArgumentsHashMap = commandWithArgumentsHashMap;
    }

    /**
     * Метод, исполняющий команду. Выводит описание всех доступных в программе команд.
     */
    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : commandWithoutArgumentsHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
        for (Map.Entry<String, CommandWithArguments> entry : commandWithArgumentsHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

    /**
     * Метод, возвращающий описание команды.
     *
     * @return Возвращает описание команды help.
     */
    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    /** Метод, возвращающий название команды.
     *
     * @return Возвращает название команды help.
     */
    @Override
    public String getName() {
        return "help";
    }
}
