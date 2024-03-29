package org.example.commands;


import org.example.dataBase.DBManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;

/**
 * Команда для удаления элемента коллекции по его id.
 */
public class RemoveById extends AbstractCommand {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public RemoveById(CollectionManager collectionManager, DBManager dbManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", 1, collectionManager, dbManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет удаление элемента коллекции по его id.
     * @param request запрос, содержащий id элемента для удаления
     * @return ответ с информацией об успешности выполнения операции
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())) {
                if (dbManager.checkRouteExistence(request.getNumericArgument())) {
                    if (dbManager.removeById(Math.toIntExact(request.getNumericArgument()), request.getLogin())) {
                        collectionManager.removeById(request.getNumericArgument());
                        return new Response("Элемент с ИД " + request.getNumericArgument() + " был удален из коллекции.");
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