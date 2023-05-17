package org.example.utill;

import org.example.ServerApp;
import org.example.commands.AbstractCommand;
import org.example.exceptions.DisconnectInitException;

/**
 * Класс для построения ответа на запрос клиента, используя переданную команду и запрос.
 */
public class RequestBuilder {
    /**
     * Строит ответ на запрос клиента, используя переданную команду и запрос.
     * Если команда требует аргумента организации, устанавливает у пути новый id, сгенерированный менеджером коллекции.
     * @param command команда для выполнения
     * @param request запрос клиента
     * @return ответ на запрос клиента
     * @throws DisconnectInitException если возникли проблемы с инициализацией отключения
     */
    public static Response build(AbstractCommand command, Request request) throws DisconnectInitException {
        if (request.getRouteArgument() != null)
            request.getRouteArgument().setId(ServerApp.collectionManager.generateNextId());
        return command.execute(request);
    }
}
