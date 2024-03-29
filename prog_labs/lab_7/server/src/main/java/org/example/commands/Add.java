package org.example.commands;

import org.example.data.Route;
import org.example.dataBase.DBManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;

/**
 * Класс отвечает за добавление пути в коллекцию
 */
public class Add extends AbstractCommand {
    /**
     * Создает новый объект команды.
     */
    public Add(CollectionManager collectionManager, DBManager dbManager) {
        super("add", "добавить новый элемент в коллекцию", 0, collectionManager, dbManager);
    }

    /**
     * Выполняет команду добавления элемента в коллекцию.
     * @param request объект запроса
     * @return объект ответа
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())) {
                Route routeToAdd = request.getRouteArgument();
                Long id = dbManager.addElement(routeToAdd, request.getLogin());
                routeToAdd.setId(id);
                collectionManager.addToCollection(routeToAdd);
                return new Response("Элемент был успешно добавлен с ИД: " + id);
            } else {
                return new Response("Несоответствие логина и пароля.");
            }
        } catch (DatabaseException e) {
            return new Response(e.getMessage());
        }
    }
}