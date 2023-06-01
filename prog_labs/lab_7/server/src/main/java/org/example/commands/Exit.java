package org.example.commands;

import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Класс команды, завершающей программу.
 */
public class Exit extends AbstractCommand {

    /**
     * Создает новый объект команды.
     */
    public Exit() {
        super("exit", "завершить программу", 0);
    }

    /**
     * Завершает выполнение программы на сервере.
     * @param request объект запроса
     * @return сообщение об отключении клиента
     */
    @Override
    public Response execute(Request request) {
        return new Response(TextWriter.getWhiteText("Отключение клиента."));
    }
}