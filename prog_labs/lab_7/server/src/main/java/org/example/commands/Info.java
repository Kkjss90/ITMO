package org.example.commands;

import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;

/**
 * Класс, представляющий команду, которая выводит информацию о коллекции.
 */
public class Info extends AbstractCommand {
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Создает новый объект команды.
     * @param collectionManager менеджер коллекции
     */
    public Info(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", 0);
        this.collectionManager = collectionManager;
    }

    /**
     * Получает информацию о коллекции.
     *
     * @param request объект запроса
     * @return ответ на выполнение команды
     */
    @Override
    public Response execute(Request request) {
        return new Response(collectionManager.getInfo());
    }
}