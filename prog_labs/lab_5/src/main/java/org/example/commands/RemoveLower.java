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
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньше заданного.
 */
public class RemoveLower extends AbstractCommand {
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
    public RemoveLower(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньше заданного");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     * Удаляет из коллекции все элементы, меньше заданного.
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Route routeToFind = new Route(
                    collectionManager.generateNextId(),
                    routeAsker.askName(),
                    routeAsker.askCoordinates(),
                    new Date(),
                    routeAsker.askLocation(),
                    routeAsker.askPosition(),
                    routeAsker.askDistance()
            );
            Route routeFromCollection = collectionManager.getByValue(routeToFind);
            if (routeFromCollection == null) throw new RouteNotFoundException();
            collectionManager.removeLower(routeFromCollection);
            Console.println("Солдаты успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (RouteNotFoundException exception) {
            Console.printerror("Солдата с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
