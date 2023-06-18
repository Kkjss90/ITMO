package org.example.commands;


import org.example.data.Route;
import org.example.exceptions.*;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreater extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный.
     * @return Статус выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            if (dbManager.validateUser(request.getLogin(), request.getPassword())){
                long distance = request.getNumericArgument();
                collectionManager.removeGreater(distance);
                Collection<Route> routeToRemove = collectionManager.getCollection().stream()
                        .filter(Objects::nonNull)
                        .filter(route -> route.compareTo(request.getRouteArgument())<1)
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
