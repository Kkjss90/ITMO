package org.example.commands;


import org.example.data.Route;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

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
    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет удаление элемента коллекции по его id.
     * @param request запрос, содержащий id элемента для удаления
     * @return ответ с информацией об успешности выполнения операции
     */
    @Override
    public Response execute(Request request) {
        int id = Math.toIntExact(request.getNumericArgument());
        Route routeToRemove = collectionManager.getById(id);
        if (routeToRemove == null)
            return new Response(TextWriter.getRedText("Пути с таким id не существует."));
        else {
            collectionManager.removeById(id);
            return new Response(TextWriter.getWhiteText("Путь был удалена."));
        }
    }
}