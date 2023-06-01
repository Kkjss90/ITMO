package org.example.utill;

import org.example.commands.*;
import org.example.utill.CollectionManager;

import java.util.*;

/**
 * Класс, отвечающий за оперирование командами.
 */
public class CommandManager {
    CollectionManager collectionManager;
    public static final Map<String, AbstractCommand> commands = new HashMap<String, AbstractCommand>();
    private AbstractCommand help;
    private AbstractCommand info;
    private AbstractCommand show;
    private AbstractCommand add;
    private AbstractCommand removeById;
    private AbstractCommand clear;
    private AbstractCommand exit;
    private AbstractCommand executeScript;
    private AbstractCommand removeGreater;
    private AbstractCommand removeLower;
    private AbstractCommand shuffle;
    private AbstractCommand filterByDistance;
    private AbstractCommand removeAnyByDistance;
    private AbstractCommand groupCountingByDistance;
    private AbstractCommand update;

    /**
     * Конструктор класса менеджера команд
     * @param collectionManager менеджер для работы с коллекцией
     */

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.help = new Help();
        this.info = new Info(collectionManager);
        this.show = new Show(collectionManager);
        this.add = new Add(collectionManager);
        this.removeById = new RemoveById(collectionManager);
        this.clear = new Clear(collectionManager);
        this.exit = new Exit();
        this.executeScript = new ExecuteScript();
        this.removeGreater = new RemoveGreater(collectionManager);
        this.removeLower = new RemoveLower(collectionManager);
        this.shuffle = new Shuffle(collectionManager);
        this.filterByDistance = new FilterByDistance(collectionManager);
        this.removeAnyByDistance = new RemoveAnyByDistance(collectionManager);
        this.groupCountingByDistance = new GroupCountingByDistance(collectionManager);
        this.update = new UpdateId(collectionManager);
        commands.put("help", help);
        commands.put("info",info);
        commands.put("show",show);
        commands.put("add",add);
        commands.put("remove_by_id",removeById);
        commands.put("clear",clear);
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
        this.add = new Add(collectionManager);
        this.removeById = new RemoveById(collectionManager);
        this.clear = new Clear(collectionManager);
        this.exit = new Exit();
        this.executeScript = new ExecuteScript();
        this.removeGreater = new RemoveGreater(collectionManager);
        this.removeLower = new RemoveLower(collectionManager);
        this.shuffle = new Shuffle(collectionManager);
        this.filterByDistance = new FilterByDistance(collectionManager);
        this.removeAnyByDistance = new RemoveAnyByDistance(collectionManager);
        this.groupCountingByDistance = new GroupCountingByDistance(collectionManager);
        this.update = new UpdateId(collectionManager);
        commands.put("help", help);
        commands.put("info",info);
        commands.put("show",show);
        commands.put("add",add);
        commands.put("remove_by_id",removeById);
        commands.put("clear",clear);
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
    public Map<String,AbstractCommand> getCommands() {
        return commands;
    }

    /**
     * Выводится, если команды не существует.
     * @param command команда, которой не существует.
     * @return Статус выполнения команды.
     */
    public boolean noSuchCommand(String command) {
        TextWriter.printErr("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }
    /**
     * Метод для получения объекта команды по имени команды из запроса.
     * @param request запрос от клиента
     * @return объект команды, соответствующий имени команды из запроса
     */
    public AbstractCommand initCommand(Request request) {
        String commandName = request.getCommandName();
        return commands.get(commandName);
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}
