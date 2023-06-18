package org.example.commands;

import org.example.data.Route;
import org.example.dataBase.DBManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

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
     *
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public RemoveAnyByDistance(CollectionManager collectionManager, DBManager dbManager) {
        super("remove_any_by_distance <DISTANCE>", "удалить элемент из коллекции по DISTANCE", 1, collectionManager, dbManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет элемент из коллекции по DISTANCE.
     *
     * @return Статус выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())) {

                long distance = request.getNumericArgument();
                Route[] routes = collectionManager.getByDistance(distance);
                for (Route routeToRemove : routes) {
                    if (dbManager.removeById(routeToRemove.getId(), request.getLogin())) {
                        collectionManager.removeById(routeToRemove.getId());
                    }
                    TextWriter.getWhiteText("Путь успешно удален!");
                }
                return new Response("");
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        return new Response("");
    }
}