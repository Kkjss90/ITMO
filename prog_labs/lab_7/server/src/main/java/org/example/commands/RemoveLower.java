package org.example.commands;


import org.example.data.Route;
import org.example.exceptions.*;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньше заданного.
 */
public class RemoveLower extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public RemoveLower(CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньше заданного", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет из коллекции все элементы, меньше заданного.
     * @return Статус выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())){
                long distance = request.getNumericArgument();
                collectionManager.removeLower(distance);
                Collection<Route> routeToRemove = collectionManager.getCollection().stream()
                        .filter(Objects::nonNull)
                        .filter(route -> route.compareTo(request.getRouteArgument())>=1)
                        .filter((obj) -> {
                            try {
                                return dbManager.removeById(obj.getId(), request.getLogin());
                            } catch (DatabaseException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
            }else {
                return new Response("Несоответствие логина и пароля.");
            }
            return new Response("");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
