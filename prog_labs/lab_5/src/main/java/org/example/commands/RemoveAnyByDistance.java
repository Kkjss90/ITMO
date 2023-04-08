package org.example.commands;

import org.example.data.Route;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.RouteNotFoundException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.CollectionManager;
import org.example.utility.Console;

/**
 * Команда 'remove_any_by_distance'. Удаляет элемент из коллекции по DISTANCE.
 */
public class RemoveAnyByDistance extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public RemoveAnyByDistance(CollectionManager collectionManager) {
        super("remove_any_by_distance <DISTANCE>", "удалить элемент из коллекции по DISTANCE");
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент из коллекции по DISTANCE.
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            long distance = Long.parseLong(argument);
            Route[] routes = collectionManager.getByDistance(distance);
            for (Route routeToRemove : routes) {
                collectionManager.removeFromCollection(routeToRemove);
            }
            Console.println("Солдат успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (RouteNotFoundException exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        }
        return false;
    }
}
