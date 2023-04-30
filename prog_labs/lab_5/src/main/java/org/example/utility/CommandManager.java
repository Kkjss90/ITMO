package org.example.utility;

import org.example.commands.*;

import java.util.*;

/**
 * Класс, отвечающий за оперирование командами.
 */
public class CommandManager {
    RouteAsker routeAsker;
    CollectionManager collectionManager;
    private Map<String, Command> commands = new HashMap<String, Command>();
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
     * @param collectionManager менеджер для работы с коллекцией
     */

    public CommandManager(CollectionManager collectionManager) {
        this.routeAsker = new RouteAsker( new Scanner(System.in));
        this.collectionManager = collectionManager;
        this.help = new Help();
        this.info = new Info(collectionManager);
        this.show = new Show(collectionManager);
        this.add = new Add(collectionManager, routeAsker);
        this.removeById = new RemoveById(collectionManager);
        this.clear = new Clear(collectionManager);
        this.save = new Save(collectionManager);
        this.exit = new Exit();
        this.executeScript = new ExecuteScript();
        this.removeGreater = new RemoveGreater(collectionManager, routeAsker);
        this.removeLower = new RemoveLower(collectionManager, routeAsker);
        this.shuffle = new Shuffle(collectionManager);
        this.filterByDistance = new FilterByDistance(collectionManager);
        this.removeAnyByDistance = new RemoveAnyByDistance(collectionManager);
        this.groupCountingByDistance = new GroupCountingByDistance(collectionManager);
        this.update = new UpdateId(collectionManager, routeAsker);
        commands.put("help", help);
        commands.put("info",info);
        commands.put("show",show);
        commands.put("add",add);
        commands.put("remove_by_id",removeById);
        commands.put("clear",clear);
        commands.put("save",save);
        commands.put("exit",exit);
        commands.put("execute_script",executeScript);
        commands.put("remove_greater",removeGreater);
        commands.put("remove_lower",removeLower);
        commands.put("shuffle",shuffle);
        commands.put("filter_by_distance",filterByDistance);
        commands.put("remove_any_by_distance",removeAnyByDistance);
        commands.put("group_counting_by_distance",groupCountingByDistance);
        commands.put("update_id",update);
    }

    public CommandManager() {
        this.help = new Help();
        this.info = new Info(collectionManager);
        this.show = new Show(collectionManager);
        this.add = new Add(collectionManager, routeAsker);
        this.removeById = new RemoveById(collectionManager);
        this.clear = new Clear(collectionManager);
        this.save = new Save(collectionManager);
        this.exit = new Exit();
        this.executeScript = new ExecuteScript();
        this.removeGreater = new RemoveGreater(collectionManager, routeAsker);
        this.removeLower = new RemoveLower(collectionManager, routeAsker);
        this.shuffle = new Shuffle(collectionManager);
        this.filterByDistance = new FilterByDistance(collectionManager);
        this.removeAnyByDistance = new RemoveAnyByDistance(collectionManager);
        this.groupCountingByDistance = new GroupCountingByDistance(collectionManager);
        this.update = new UpdateId(collectionManager, routeAsker);
        commands.put("help", help);
        commands.put("info",info);
        commands.put("show",show);
        commands.put("add",add);
        commands.put("remove_by_id",removeById);
        commands.put("clear",clear);
        commands.put("save",save);
        commands.put("exit",exit);
        commands.put("execute_script",executeScript);
        commands.put("remove_greater",removeGreater);
        commands.put("remove_lower",removeLower);
        commands.put("shuffle",shuffle);
        commands.put("filter_by_distance",filterByDistance);
        commands.put("remove_any_by_distance",removeAnyByDistance);
        commands.put("group_counting_by_distance",groupCountingByDistance);
        commands.put("update_id",update);
    }

    /**
     * @return массив команд.
     */
    public Map<String,Command> getCommands() {
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

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}
