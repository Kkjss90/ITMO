package org.example.commands;

import org.example.dataBase.DBManager;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Класс Shuffle случайным образом перемешивает элементы коллекции
 */
public class Shuffle extends AbstractCommand {

    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public Shuffle(CollectionManager collectionManager, DBManager dbManager) {
        super("shuffle", "перемешать элементы коллекции в случайном порядке", 0, collectionManager, dbManager);
        this.collectionManager = collectionManager;
    }

    /**
     * Перемешивает элементы коллекции
     *
     * @param request объект запроса
     * @return объект ответа
     */
    @Override
    public Response execute(Request request) {
        if (collectionManager.getCollection().isEmpty())
            return new Response(TextWriter.getRedText("Коллекция пуста."));
        else {
            collectionManager.shuffle();
            return new Response(TextWriter.getWhiteText("Коллекция перемешана."));
        }
    }
}