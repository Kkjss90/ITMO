package org.example.utility;

import org.example.commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за оперирование командами.
 */
public class CommandManager {

    private List<Command> commands = new ArrayList<>();
    private Command help;
    private Command info;
    private Command show;
    private Command add;
    private Command removeById;
    private Command clear;
    private Command save;
    private Command exit;
    private Command executeScript;
    private Command removeGreater;
    private Command removeLower;
    private Command shuffle;
    private Command filterByDistance;
    private Command removeAnyByDistance;
    private Command groupCountingByDistance;
    private Command update;

    /**
     * Конструктор класса менеджера команд
     * @param help команда вывода справки
     * @param info команда вывода информации о коллекции
     * @param show команда вывода всех элементов коллекции
     * @param add команда добавления нового пути в коллекцию
     * @param removeById команда удаления элемента коллекции по заданному id
     * @param clear команда очистки коллекции
     * @param save команда сохранения коллекции в файл
     * @param exit команда выхода из программы
     * @param executeScript команда исполнения скрипта
     * @param removeGreater команда удаления объекта больше заданного
     * @param removeLower команда удаления объекта меньше заданного
     * @param shuffle команда перемешивания элементов коллекции
     * @param filterByDistance команда фильтрации объектов по полю distance
     * @param removeAnyByDistance команда удаления объектов по полю distance
     * @param groupCountingByDistance команда группировки объектов по значению полю distance
     * @param update команда обновления значений элемента коллекции по заданному id
     */
    public CommandManager(Command help, Command info, Command show, Command add, Command removeById, Command clear, Command save, Command exit, Command executeScript, Command removeGreater, Command removeLower, Command shuffle, Command filterByDistance, Command removeAnyByDistance, Command groupCountingByDistance, Command update) {
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.removeById = removeById;
        this.clear = clear;
        this.save = save;
        this.exit = exit;
        this.executeScript = executeScript;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.shuffle = shuffle;
        this.filterByDistance = filterByDistance;
        this.removeAnyByDistance = removeAnyByDistance;
        this.groupCountingByDistance = groupCountingByDistance;
        this.update = update;
        commands.add(help);
        commands.add(info);
        commands.add(show);
        commands.add(add);
        commands.add(removeById);
        commands.add(clear);
        commands.add(save);
        commands.add(exit);
        commands.add(executeScript);
        commands.add(removeGreater);
        commands.add(removeLower);
        commands.add(shuffle);
        commands.add(filterByDistance);
        commands.add(removeAnyByDistance);
        commands.add(groupCountingByDistance);
        commands.add(update);
    }




    /**
     * @return массив команд.
     */
    public List<Command> getCommands() {
        return commands;
    }


    /**
     * Выводится, если команды не существует.
     * @param command команда, которой не существует.
     * @return Статус выполнения команды.
     */
    public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    /**
     * Выводит информацию по всем командам.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean help(String argument) {
        if (help.execute(argument)) {
            for (Command command : commands) {
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Выводит информацию о коллекции.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean info(String argument) {
        return info.execute(argument);
    }

    /**
     * Выводит все элементы коллекции.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean show(String argument) {
        return show.execute(argument);
    }

    /**
     * Добавить новый элемент в коллекцию.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean add(String argument) {
        return add.execute(argument);
    }

    /**
     * Обновить значение элемента коллекции по ID.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean update(String argument) {
        return update.execute(argument);
    }

    /**
     * Удалить элемент из коллекции по ID.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean removeById(String argument) {
        return removeById.execute(argument);
    }

    /**
     * Очистить коллекцию.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean clear(String argument) {
        return clear.execute(argument);
    }

    /**
     * Сохранить коллекцию в файл.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean save(String argument) {
        return save.execute(argument);
    }

    /**
     * Завершить программу (без сохранения в файл).
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean exit(String argument) {
        return exit.execute(argument);
    }

    /**
     * Исполнить скрипт из указанного файла.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean executeScript(String argument) {
        return executeScript.execute(argument);
    }


    /**
     * Удалить из коллекции все элементы, превышающие заданный.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean removeGreater(String argument) {
        return removeGreater.execute(argument);
    }

    /**
     * Удалить из коллекции все элементы, меньше заданного.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean removeLower(String argument){
        return removeLower.execute(argument);
    }

    /**
     * Перемешать элементы коллекции в случайном порядке.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean shuffle(String argument){
        return shuffle.execute(argument);
    }

    /**
     * Вывести элементы, значение поля distance которых равно заданному.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean filterByDistance(String argument){
        return filterByDistance.execute(argument);
    }

    /**
     * Удалить все элементы из коллекции по DISTANCE.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean removeAnyByDistance(String argument){
        return removeAnyByDistance.execute(argument);
    }
    /**
     * Сгруппировать элементы коллекции по значению поля distance, вывести количество элементов в каждой группе.
     * @param argument аргумент.
     * @return Статус выполнения команды.
     */
    public boolean groupCountingByDistance(String argument){
        return groupCountingByDistance.execute(argument);
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}
