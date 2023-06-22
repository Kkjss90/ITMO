package org.example.commands;


import org.example.data.Route;
import org.example.dataBase.DBManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Команда очистки коллекции.
 */
public class Clear extends AbstractCommand {

    public Clear(CollectionManager collectionManager, DBManager dbManager) {
        super("clear", "очистить коллекцию", 0, collectionManager, dbManager);
    }

    /**
     * Очищает коллекцию, если она не пуста.
     * @param request объект запроса
     * @return объект Response с сообщением об успешном выполнении или ошибке, если коллекция пуста
     */
    @Override
    public Response execute(Request request) {
        try {
            getDbManager().clear(request.getLogin());

            if (collectionManager.getCollection().isEmpty())
                return new Response(TextWriter.getRedText("Коллекция пуста."));
            else {
                collectionManager.clearCollection();
                collectionManager.setCollection(dbManager.loadCollection());
                return new Response(TextWriter.getWhiteText("Коллекция успешно очищена."));
            }
        } catch (DatabaseException e) {
            return new Response(e.getMessage());
        }
    }
}