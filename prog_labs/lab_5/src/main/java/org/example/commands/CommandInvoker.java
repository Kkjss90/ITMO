package commands;

import io.*;
import collection.*;
import file.RouteFieldsReader;
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

}
