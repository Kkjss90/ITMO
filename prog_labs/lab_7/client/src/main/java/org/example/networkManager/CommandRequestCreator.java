package org.example.networkManager;


import org.example.dispatcher.AvailableCommands;
import org.example.dispatcher.CommandToSend;
import org.example.dispatcher.CommandValidators;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.WrongAmountOfArgumentsException;
import org.example.exceptions.WrongArgException;
import org.example.utill.Request;
import org.example.utill.RequestType;
import org.example.utill.ScannerManager;
import org.example.utill.TextWriter;

import java.util.Scanner;

/**
 * Класс для создания запроса по команде, полученной от пользователя.
 */
public class CommandRequestCreator {

    /**
     * Создает запрос по переданной команде.
     * @param command команда
     * @param scanner сканер
     * @param scriptMode режим скрипта
     * @return запрос
     * @throws NullPointerException если команда не найдена
     */
    public Request createRequestOfCommand(CommandToSend command, Scanner scanner, boolean scriptMode) throws NullPointerException {
        String name = command.getCommandName();
        Request request;
        if (AvailableCommands.COMMANDS_WITHOUT_ARGS.contains(name)) {
            request = createRequestWithoutArgs(command);
        } else if (AvailableCommands.COMMANDS_WITH_ID_ARG.contains(name)) {
            request = createRequestWithID(command);
        } else if (AvailableCommands.COMMANDS_WITH_ROUTE_ARG.contains(name)) {
            request = createRequestWithRoute(command, scanner, scriptMode);
        } else if (AvailableCommands.COMMANDS_WITH_ROUTE_ID_ARGS.contains(name)) {
            request = createRequestWithRouteID(command, scanner, scriptMode);
        } else if (AvailableCommands.SCRIPT_ARGUMENT_COMMAND.contains(name)) {
            request = createRequestWithRouteID(command, scanner, scriptMode);
        } else {
            throw new NullPointerException("Команда не найдена. Напишите 'help' для просмотра всех доступных команд.");
        }
        return request;
    }

    /**
     * Создает запрос без аргументов.
     * @param command команда
     * @return запрос
     */
    private Request createRequestWithoutArgs(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 0);
            return new Request(command.getCommandName(), RequestType.COMMAND);
        } catch (WrongAmountOfArgumentsException e) {
            TextWriter.printErr(e.getMessage());
            return null;
        }
    }

    /**
     * Создает запрос с аргументом ID.
     * @param command команда
     * @return запрос
     */
    private Request createRequestWithID(CommandToSend command) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            long id = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "ИД должен быть больше 0.",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), id, RequestType.COMMAND);
        } catch (WrongAmountOfArgumentsException | WrongArgException e) {
            TextWriter.printErr(e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            TextWriter.printErr("Неправильный тип данных аргумента.");
            return null;
        }
    }

    /**
     * Класс для создания запроса с аргументом типа Organization.
     * @param command команда
     * @param sc сканер
     * @param scriptMode режим скрипта
     * @return запрос
     */
    private Request createRequestWithRoute(CommandToSend command, Scanner sc, boolean scriptMode) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 0);
            ScannerManager scannerManager = new ScannerManager(sc, scriptMode);
            return new Request(command.getCommandName(), scannerManager.askRoute(), RequestType.COMMAND);
        } catch (WrongAmountOfArgumentsException | IncorrectInputInScriptException e) {
            TextWriter.printErr("Проверьте правильность данных в скрипте. Остановка приложения.");
            System.exit(1);
            return null;
        }
    }

    /**
     * Класс для создания запроса с аргументом типа Organization и ID.
     * @param command команда
     * @param sc сканер
     * @param scriptMode режим скрипта
     * @return запрос
     */
    private Request createRequestWithRouteID(CommandToSend command, Scanner sc, boolean scriptMode) {
        try {
            CommandValidators.validateAmountOfArgs(command.getCommandArgs(), 1);
            ScannerManager scannerManager = new ScannerManager(sc, scriptMode);
            long id = CommandValidators.validateArg(arg -> ((long) arg) > 0,
                    "ID должен быть больше 0",
                    Long::parseLong,
                    command.getCommandArgs()[0]);
            return new Request(command.getCommandName(), id, scannerManager.askRoute(), RequestType.COMMAND);
        } catch (WrongAmountOfArgumentsException | WrongArgException | IllegalArgumentException | IncorrectInputInScriptException e) {
            TextWriter.printErr("Проверьте правильность данных в скрипте. Остановка приложения.");
            System.exit(1);
            return null;
        }
    }
}