package org.example.commands;


import org.example.dataBase.DBManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;

import java.util.List;

/**
 * Команда, которая выводит все элементы коллекции.
 */
public class Show extends AbstractCommand {
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public Show(CollectionManager collectionManager, DBManager dbManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0, collectionManager, dbManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который выполняет команду.
     * @param request объект запроса
     * @return строку, содержащую все элементы коллекции.
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())) {
                if (collectionManager.getCollection().isEmpty()) {
                    return new Response("Коллекция пуста.");
                } else {
                    List<Integer> ids = dbManager.getIdsOfUsersElements(request.getLogin());
                    return new Response("Элементы коллекции:",
                            collectionManager.getUsersElements(ids),
                            collectionManager.getAlienElements(ids));
                }
            } else {
                return new Response("Несоответствие логина и пароля.");
            }

        } catch (DatabaseException e) {
            return new Response(e.getMessage());
        }
    }
}
