package org.example.commands;

import org.example.data.Coordinates;
import org.example.data.Location;
import org.example.data.Position;
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
 * Команда 'update_id'. Обновляет значение элемента пути по его ID.
 */
public class UpdateId extends AbstractCommand {
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
    public UpdateId(CollectionManager collectionManager, RouteAsker routeAsker) {
        super("update_id <ID> {element}", "обновить значение элемента коллекции по ID");
        this.collectionManager = collectionManager;
        this.routeAsker = routeAsker;
    }

    /**
     * Обновляет значение элемента пути по его ID.
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            int id = Integer.parseInt(argument);
            Route oldRoute = collectionManager.getById(id);
            if (oldRoute == null) throw new RouteNotFoundException();

            String name = oldRoute.getName();
            Coordinates coordinates = oldRoute.getCoordinates();
            Date creationDate = oldRoute.getCreationDate();
            Location from = oldRoute.getFrom();
            Position to = oldRoute.getTo();
            long distance = oldRoute.getDistance();

            collectionManager.removeFromCollection(oldRoute);

            if (routeAsker.askQuestion("Хотите изменить имя пути?")) name = routeAsker.askName();
            if (routeAsker.askQuestion("Хотите изменить координаты пути?")) coordinates = routeAsker.askCoordinates();
            if (routeAsker.askQuestion("Хотите изменить начало пути?")) from = routeAsker.askLocation();
            if (routeAsker.askQuestion("Хотите изменить конец пути?")) to = routeAsker.askPosition();
            if (routeAsker.askQuestion("Хотите изменить дистанцию пути?")) distance = routeAsker.askDistance();

            collectionManager.addToCollection(new Route(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    from,
                    to,
                    distance
            ));
            Console.println("Солдат успешно изменен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (RouteNotFoundException exception) {
            Console.printerror("Пути с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
