package org.example.commands;

import org.example.data.Route;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.CollectionManager;
import org.example.utility.Console;
import org.example.utility.RouteAsker;

import java.util.Date;

/**
 * Команда 'Add'. Добавляет новый элемент в коллекцию.
 */
public class Add extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;

    /**
     Объект, задающий вопросы пользователю для ввода информации о пути.
     */
    private RouteAsker routeAsker;

    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     * @param routeAsker объект, который задает параметры нового элемента коллекции.
     */
    public Add(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     * Добавляет новый элемент в коллекцию.
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new Route(
                    collectionManager.generateNextId(),
                    routeAsker.askName(),
                    routeAsker.askCoordinates(),
                    new Date(),
                    routeAsker.askLocation(),
                    routeAsker.askPosition(),
                    routeAsker.askDistance()
            ));
            Console.println("Путь успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
