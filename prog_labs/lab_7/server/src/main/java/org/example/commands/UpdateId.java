package org.example.commands;

import org.example.data.Route;
import org.example.dataBase.DBManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Команда для обновления значения элемента коллекции, id которого равен заданному.
 */
public class UpdateId extends AbstractCommand {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public UpdateId(CollectionManager collectionManager, DBManager dbManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", 1, collectionManager, dbManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который выполняет команду.
     *
     * @param request объект запроса
     * @return объект ответа
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())) {
                Long id = request.getNumericArgument();
                if (dbManager.checkRouteExistence(id)) {
                    if (dbManager.updateById(request.getRouteArgument(), id, request.getLogin())) {
                        Route updatedRoute = request.getRouteArgument();
                        updatedRoute.setId(Math.toIntExact(id));
                        collectionManager.removeById(Math.toIntExact(id));
                        collectionManager.addToCollection(request.getRouteArgument());
                        return new Response("Элемент с ИД " + id + " был успешно обновлен.");
                    } else {
                        return new Response("Элемент был создан другим пользователем. У вас нет прав для его обновления.");
                    }
                } else {
                    return new Response("Нет элемента с таким идентификатором.");
                }
            } else {
                return new Response("Несоответствие логина и пароля.");
            }
        } catch (DatabaseException e) {
            return new Response(e.getMessage());
        }
    }
}