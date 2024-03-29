package org.example.requestHandlers;

import org.example.ServerConfig;
import org.example.utill.Request;
import org.example.utill.RequestType;
import org.example.utill.Response;

import java.util.function.Function;

/**
 * Класс-обработчик запросов клиентов, реализующий интерфейс Function Request, Response.
 */
public class RequestExecutor implements Function<Request, Response> {

    /**
     * Обработчик запросов.
     * @param request запрос клиента.
     * @return ответ сервера на запрос клиента.
     */
    @Override
    public Response apply(Request request) {
        if (request != null) {
            if (request.getType().equals(RequestType.COMMAND)) {
                return ServerConfig.commandManager.initCommand(request).execute(request);
            } else if (request.getType().equals(RequestType.REGISTER)) {
                return ServerConfig.usersManager.registerUser(request);
            } else {
                return ServerConfig.usersManager.logInUser(request);
            }
        } else return null;
    }
}