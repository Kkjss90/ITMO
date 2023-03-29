package org.example.run;

import org.example.collections.Route;
import org.example.collections.CollectionManager;
import org.example.commands.CommandInvoker;
import org.example.file.JsonParser;
import org.example.file.RouteFieldsReader;
import org.example.file.data.Data;
import org.example.file.data.Fs;
import org.example.io.UserIO;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Класс, через который производится запуск данного приложения.
 */
public class Application {
    /**
     * менеджер коллекций
     */
    CollectionManager collectionManager;
    /**
     * json парсер
     */
    JsonParser jsonParser;
    /**
     * менеджер данных
     */
    Data data;
    /**
     * хранит ссылку на объект, производящий чтение и вывод команд
     */
    UserIO userIO;
    /**
     * хранит ссылку на объект, который производит запуск выбранных команд
     */
    CommandInvoker commandInvoker;
    /**
     * хранит ссылку на объект, производящий чтение полей класса Dragon
     */
    RouteFieldsReader routeFieldsReader;

    /**
     * Метод, выполняющий запуск программы. Через него происходит работа всей программы.
     *
     * @param inputFile имя файла, откуда следует читать объекты коллекции, представленные в формате XML
     */
    public void start(String inputFile) {

        collectionManager = new CollectionManager();
        data = new Fs(inputFile);
        userIO = new UserIO();

        routeFieldsReader = new RouteFieldsReader(userIO);

        this.commandInvoker = new CommandInvoker(collectionManager, userIO, inputFile, routeFieldsReader);

        try {
            File ioFile = new File(inputFile);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            String file = data.getData();

            Vector<Route> routes= jsonParser.parseToCollection(file);
            for (Route route : routes) {
                collectionManager.insert(Math.toIntExact(route.getId()), route);
            }
            userIO.printCommandText("Элементы коллекций из указанного файла были загружены\n");
        } catch (IOException e) {
            System.err.println("По указанному адресу нет подходящего файла");
            System.exit(0);
        }
        try {
            cycle();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }
    }

    /**
     * Метод, выполняющий циклическое чтение команд из строки ввода
     */
    public void cycle() {
        userIO.printCommandText("Программа была запущена.\n");
        while (true) {
            userIO.printCommandText("\nВведите название команды:\n");
            userIO.printPreamble();
            String line = userIO.readLine();
            commandInvoker.execute(line);
        }
    }
}