package org.example.commands;


import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;

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
    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который выполняет команду.
     * @param request объект запроса
     * @return строку, содержащую все элементы коллекции.
     */
    @Override
    public Response execute(Request request) {
        return new Response(collectionManager.show());
    }
}
