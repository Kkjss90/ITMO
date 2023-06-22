package org.example.commands;


import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Класс команды для выполнения скрипта из указанного файла.
 */
public class ExecuteScript extends AbstractCommand {

    /**
     * Создает новый объект команды.
     */
    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла", 0);
    }

    /**
     * Исполняет скрипт.
     * @param request объект запроса
     * @return объект Response с сообщением о результате выполнения команды
     */
    @Override
    public Response execute(Request request) {
        return new Response(TextWriter.getWhiteText("Исполнение скрипта."));
    }

}