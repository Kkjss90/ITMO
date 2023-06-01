package org.example.commands;

import org.example.utill.CommandManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;



/*
 * Команда 'help'. Вывести справку по доступным командам.
 */
public class Help extends AbstractCommand {
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     */
    public Help() {
        super("help", "вывести справку по доступным командам", 0);
    }

    /**
     * Выполняет команду вывода списка доступных команд.
     *
     * @param request объект запроса
     * @return объект ответа с информацией о доступных командах
     */
    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        for (AbstractCommand command : CommandManager.commands.values()) {
            sb.append(command.toString()).append("\n");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        return new Response(TextWriter.getWhiteText("Доступные команды:\n") + sb);
    }
}