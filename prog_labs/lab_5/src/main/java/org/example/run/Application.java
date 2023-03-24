package org.example.run;

import org.example.collections.Route;
import org.example.collections.CollectionManager;
import org.example.commands.CommandInvoker;
import org.example.file.RouteFieldsReader;
import org.example.file.data.Data;
import org.example.file.data.Fs;
import org.example.io.UserIO;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.NoSuchElementException;

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
    file.JsonParser jsonParser;
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

        dragonFieldsReader = new DragonFieldsReader(userIO);

        this.commandInvoker = new CommandInvoker(collectionManager, userIO, inputFile, dragonFieldsReader);

        try {

            File ioFile = new File(inputFile);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            String file = fileManager.readFromFile(inputFile);

            Dragon[] dragons = xmlParser.parseToCollection(new InputSource(new StringReader(file)));
            for (Dragon dragon : dragons) {
                collectionManager.insert(dragon.getId(), dragon);
            }
            userIO.printCommandText("Элементы коллекций из указанного файла были загружены\n");
        } catch (ParserConfigurationException | IOException | SAXException e) {
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