package org.example.commands;

import org.example.data.Route;
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
    public UpdateId(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", 1);
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
        int id = Math.toIntExact(request.getNumericArgument());
        Route routeToUpdate = collectionManager.getById(id);
        if (routeToUpdate == null)
            return new Response(TextWriter.getRedText("Пути с таким id не существует."));
        else {
            Route updatedRoute = request.getRouteArgument();
            updatedRoute.setId(id);
            collectionManager.removeById(id);
            collectionManager.addToCollection(updatedRoute);
            return new Response(TextWriter.getWhiteText("Данные пути были обновлены."));
        }
    }
}