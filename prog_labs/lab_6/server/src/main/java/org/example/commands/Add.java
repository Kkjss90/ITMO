package org.example.commands;

import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Класс отвечает за добавление пути в коллекцию
 */
public class Add extends AbstractCommand {
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public Add(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду добавления элемента в коллекцию.
     * @param request объект запроса
     * @return объект ответа
     */
    @Override
    public Response execute(Request request) {
        collectionManager.addToCollection(request.getRouteArgument());
        return new Response(TextWriter.getWhiteText("Организация добавлена в коллекцию."));
    }
}