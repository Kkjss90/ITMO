package org.example.commands;

import org.example.data.Route;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.RouteNotFoundException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.CollectionManager;
import org.example.utility.Console;
import org.example.utility.RouteAsker;

import java.util.Date;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreater extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;
    /**
     * Объект, задающий вопросы пользователю для ввода информации о пути.
     */
    private RouteAsker routeAsker;
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     * @param routeAsker Объект, задающий вопросы пользователю для ввода информации о пути.
     */
    public RemoveGreater(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный.
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            long distance = Long.parseLong(argument);
            if (distance == 0) throw new RouteNotFoundException();
            collectionManager.removeGreater(distance);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (RouteNotFoundException exception) {
            Console.printerror("Пути с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
