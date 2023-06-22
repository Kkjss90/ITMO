package org.example.utill;

import org.example.commands.*;
import org.example.dataBase.DBManager;
import org.example.utill.CollectionManager;

import java.util.*;

/**
 * Класс, отвечающий за оперирование командами.
 */
public class CommandManager {
    CollectionManager collectionManager;
    DBManager dbManager;
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

    public CommandManager(CollectionManager collectionManager, DBManager dbManager) {
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        this.help = new Help();
        this.info = new Info(collectionManager);
        this.show = new Show(collectionManager, dbManager);
        this.add = new Add(collectionManager, dbManager);
        this.removeById = new RemoveById(collectionManager, dbManager);
        this.clear = new Clear(collectionManager, dbManager);
        this.exit = new Exit();
        this.executeScript = new ExecuteScript();
        this.removeGreater = new RemoveGreater(collectionManager, dbManager);
        this.removeLower = new RemoveLower(collectionManager, dbManager);
        this.shuffle = new Shuffle(collectionManager);
        this.filterByDistance = new FilterByDistance(collectionManager, dbManager);
        this.removeAnyByDistance = new RemoveAnyByDistance(collectionManager, dbManager);
        this.groupCountingByDistance = new GroupCountingByDistance(collectionManager);
        this.update = new UpdateId(collectionManager, dbManager);
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
//
//    public CommandManager() {
//        this.help = new Help();
//        this.info = new Info(collectionManager);
//        this.show = new Show(collectionManager, dbManager);
//        this.add = new Add(collectionManager, dbManager);
//        this.removeById = new RemoveById(collectionManager, dbManager);
//        this.clear = new Clear(collectionManager, dbManager);
//        this.exit = new Exit();
//        this.executeScript = new ExecuteScript();
//        this.removeGreater = new RemoveGreater(collectionManager, dbManager);
//        this.removeLower = new RemoveLower(collectionManager, dbManager);
//        this.shuffle = new Shuffle(collectionManager);
//        this.filterByDistance = new FilterByDistance(collectionManager, dbManager);
//        this.removeAnyByDistance = new RemoveAnyByDistance(collectionManager, dbManager);
//        this.groupCountingByDistance = new GroupCountingByDistance(collectionManager);
//        this.update = new UpdateId(collectionManager, dbManager);
//        commands.put("help", help);
//        commands.put("info",info);
//        commands.put("show",show);
//        commands.put("add",add);
//        commands.put("remove_by_id",removeById);
//        commands.put("clear",clear);
//        commands.put("exit",exit);
//        commands.put("execute_script",executeScript);
//        commands.put("remove_greater",removeGreater);
//        commands.put("remove_lower",removeLower);
//        commands.put("shuffle",shuffle);
//        commands.put("filter_by_distance",filterByDistance);
//        commands.put("remove_any_by_distance",removeAnyByDistance);
//        commands.put("group_counting_by_distance",groupCountingByDistance);
//        commands.put("update_id",update);
//    }

//    public CommandManager(AbstractCommand help, AbstractCommand info, AbstractCommand show, AbstractCommand add, AbstractCommand removeById, AbstractCommand clear, AbstractCommand exit, AbstractCommand executeScript, AbstractCommand removeGreater, AbstractCommand removeLower, AbstractCommand shuffle, AbstractCommand filterByDistance, AbstractCommand removeAnyByDistance, AbstractCommand groupCountingByDistance, AbstractCommand update) {
//        this.help = help;
//        this.info = info;
//        this.show = show;
//        this.add = add;
//        this.removeById = removeById;
//        this.clear = clear;
//        this.exit = exit;
//        this.executeScript = executeScript;
//        this.removeGreater = removeGreater;
//        this.removeLower = removeLower;
//        this.shuffle = shuffle;
//        this.filterByDistance = filterByDistance;
//        this.removeAnyByDistance = removeAnyByDistance;
//        this.groupCountingByDistance = groupCountingByDistance;
//        this.update = update;
//        commands.put("help", help);
//        commands.put("info",info);
//        commands.put("show",show);
//        commands.put("add",add);
//        commands.put("remove_by_id",removeById);
//        commands.put("clear",clear);
//        commands.put("exit",exit);
//        commands.put("execute_script",executeScript);
//        commands.put("remove_greater",removeGreater);
//        commands.put("remove_lower",removeLower);
//        commands.put("shuffle",shuffle);
//        commands.put("filter_by_distance",filterByDistance);
//        commands.put("remove_any_by_distance",removeAnyByDistance);
//        commands.put("group_counting_by_distance",groupCountingByDistance);
//        commands.put("update_id",update);
//    }

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
