package org.example.commands;

import org.example.io.*;
import org.example.collections.*;
import org.example.file.RouteFieldsReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс, через который осуществляется исполнение команд. Хранит коллекции всех существующих команд.
 */
public class CommandInvoker {
    /**
     * Коллекция команд без дополнительных аргументов, которые записываются с новой строки.
     */
    private HashMap<String, Command> commandsWithoutArguments;
    /**
     * Коллекция команд с дополнительными аргументами, которые записываются с новой строки.
     */
    private HashMap<String, CommandWithArguments> commandsWithArguments;
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Поле, хранящее ссылку на объект класса UserIO.
     */
    private UserIO userIO;
    /**
     * Поле, хранящее строку, в которой записан адрес файла, куда следует сохранять полученную коллекцию (экземпляры коллекции).
     */
    private String inputFile;
    /**
     * Поле, хранящее ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    private RouteFieldsReader routeFieldsReader;
    /**
     * Поле, хранящее объект класса ExecuteScript.Script.
     */
    ExecuteScript.Script script;

    /**
     * Конструктор класса. Внутри вызывается метод putCommands, добавляющий команды в коллекции команд, создается новый объект класса ExecuteScript.Script.
     *
     * @param collectionManager  Хранит ссылку созданный в объекте Application объект CollectionManager.
     * @param userIO             Хранит ссылку на объект класса UserIO.
     * @param inputFile          Хранит строку, в которой записан адрес файла, куда следует сохранять полученную коллекцию (экземпляры коллекции).
     * @param routeFieldsReader Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, String inputFile, RouteFieldsReader routeFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.inputFile = inputFile;
        this.routeFieldsReader = routeFieldsReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = new ExecuteScript.Script();

        this.putCommands();
    }
    /**
     * Конструктор класса. Внутри вызывается метод putCommands, инициализируется поле, в которое присваивается существующий объект класса ExecuteScript.Script.
     *
     * @param collectionManager  Хранит ссылку на созданный в объекте Application объект CollectionManager.
     * @param userIO             Хранит ссылку на объект класса UserIO.
     * @param routeFieldsReader Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     * @param script             Хранит ссылку на объект класса ExecuteScript.Script.
     */
    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, RouteFieldsReader routeFieldsReader, ExecuteScript.Script script) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.routeFieldsReader = routeFieldsReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = script;
        this.putCommands();
    }
    /**
     * Метод, добавляющий команды в соответствующие им коллекции.
     */
    private void putCommands() {
        commandsWithoutArguments.put("info", new Info(collectionManager));
        commandsWithoutArguments.put("show", new Show(collectionManager));
        commandsWithoutArguments.put("clear", new Clear(collectionManager));
        //commandsWithoutArguments.put("save", new Save(collectionManager, inputFile));
        commandsWithoutArguments.put("exit", new Exit());
        commandsWithoutArguments.put("help", new Help(commandsWithoutArguments, commandsWithArguments));
        commandsWithArguments.put("execute_script", new ExecuteScript(collectionManager, routeFieldsReader, script));
    }
    /**
     * Метод, который определяет из полученной строки команду, исполняет ее и передает ей необходимые аргументы.
     * Если команда не распознана, то в стандартный поток вывода выводится соответствующее сообщение.
     *
     * @param firstCommandLine Первая строка команды, где хранится само ее название и переданные на этой же строке аргументы.
     */
    public void execute(String firstCommandLine) {
        String[] words = firstCommandLine.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length); //проверка на размер больше 1

        if (commandsWithArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            CommandWithArguments command;

            command = commandsWithArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.getCommandArguments(args);
            command.execute();
        } else if (commandsWithoutArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;

            command = commandsWithoutArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();
        } else {
            System.err.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
        }
    }


}
