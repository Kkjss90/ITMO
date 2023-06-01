package org.example.commands;


import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Команда очистки коллекции.
 */
public class Clear extends AbstractCommand {
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Очищает коллекцию, если она не пуста.
     * @param request объект запроса
     * @return объект Response с сообщением об успешном выполнении или ошибке, если коллекция пуста
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(TextWriter.getRedText("Коллекция пуста."));
        else {
            collectionManager.clearCollection();
            return new Response(TextWriter.getWhiteText("Коллекция очищена."));
        }
    }
}