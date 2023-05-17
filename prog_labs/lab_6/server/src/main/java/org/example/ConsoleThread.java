package org.example;

import org.example.data.Route;
import org.example.utill.TextWriter;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Класс, представляющий поток консоли сервера.
 */
public class ConsoleThread extends Thread {

    /**
     * Сканнер, считывающий ввод пользователя.
     */
    private static final Scanner scanner = ServerApp.scanner;

    /**
     * Состояние запущенного потока.
     */
    public volatile boolean running = true;

    /**
     * Запускает поток консоли сервера.
     */
    @Override
    public void run() {
        try {
            ServerApp.logger.info("Консоль запущена.");
            while (running) {
                String line = scanner.nextLine();
                if ("save".equalsIgnoreCase(line)) {
                    ServerApp.fileManager.writeCollection((Vector<Route>) ServerApp.collectionManager.getCollection());
                } else if ("exit".equalsIgnoreCase(line)) {
                    ServerApp.logger.info("Работа сервера завершена.");
                    System.exit(0);
                } else {
                    TextWriter.printErr("Такой команды не существует. Напишите 'save' или 'exit'.");
                }
            }
        } catch (NoSuchElementException e) {
            TextWriter.printErr("Принудительное завершение работы.");
            ServerApp.logger.severe("Работа сервера завершена.");
            System.exit(1);
        }
    }
}