package org.example;

import ch.qos.logback.classic.Logger;
import org.example.commands.AbstractCommand;
import org.example.exceptions.DisconnectInitException;
import org.example.utill.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Класс для установки соединения с клиентами.
 */
public class ServerApp {
    /**
     * Сканер для чтения ввода.
     */
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Файловый менеджер.
     */
    public static final FileManager fileManager = new FileManager(Server.fileName);

    /**
     * Менеджер коллекции.
     */
    public static CollectionManager collectionManager = new CollectionManager();

    /**
     * Состояние сервера.
     */
    public static boolean running = true;

    /**
     * Логгер для записи сообщений о работе сервера.
     */
    public static final ch.qos.logback.classic.Logger logger =  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Server.class.getName());
    static {
        try {
            ch.qos.logback.core.FileAppender<ch.qos.logback.classic.spi.ILoggingEvent> fileAppender = new ch.qos.logback.core.FileAppender<>();
            fileAppender.setFile("logs/log.log");
            fileAppender.setAppend(true);
            fileAppender.setEncoder(new ch.qos.logback.classic.encoder.PatternLayoutEncoder());
            ch.qos.logback.classic.encoder.PatternLayoutEncoder encoder = (ch.qos.logback.classic.encoder.PatternLayoutEncoder) fileAppender.getEncoder();
            encoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
            ServerApp.logger.addAppender(fileAppender);
        } catch (SecurityException e) {
            ServerApp.logger.warn("Отказано в доступе при открытии файла журнала: " + e.getMessage());
        } catch (Exception e) {
            ServerApp.logger.warn("Произошла ошибка при открытии обработчика файла журнала: " + e.getMessage());
        }
    }

    /**
     * Командный менеджер, содержащий список команд для обработки запросов клиентов.
     */
    public static CommandManager commandManager = new CommandManager(collectionManager);

    /**
     * Стандартный порт.
     */
    protected static int PORT = 65435;

    /**
     * Метод для запуска сервера.
     * @param args аргументы командной строки.
     * @param serverSocket объект ServerSocket, прослушивающий порт.
     * @throws IOException если возникли проблемы с вводом-выводом.
     */
    static void startServer(String[] args, ServerSocket serverSocket) throws IOException {
        try {
            ServerApp.logger.info("Сервер запущен.");
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (running) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(() -> {
                    try {
                        ServerApp.logger.info("Подключение клиента.");
                        startSelectorLoop(clientSocket, serverSocket);
                    } catch (SocketException e) {
                        if (e.getMessage().contains("Connection reset"));
                        else { ServerApp.logger.error(e.getMessage()); }
                    } catch (IOException | ClassNotFoundException | InterruptedException e) {
                        ServerApp.logger.error(e.getMessage());
                    }
                });
            }
        } catch (IOException e) {
            ServerApp.logger.warn("Не удается принять клиентское соединение: " + e.getMessage());
        }
    }

    /**
     * Метод, который запускает бесконечный цикл обработки запросов клиента.
     * @param socket сокет для подключения клиента.
     * @param serverSocket серверный сокет для принятия клиентских подключений.
     * @throws IOException если возникает ошибка ввода/вывода при работе с сокетами.
     * @throws ClassNotFoundException если класс не был найден при чтении объекта из потока.
     * @throws InterruptedException если поток был прерван во время ожидания ответа от клиента.
     */
    private static void startSelectorLoop(Socket socket, ServerSocket serverSocket) throws IOException, ClassNotFoundException, InterruptedException {
        while (socket.isConnected()) {
            startIteratorLoop(socket, serverSocket);
        }
    }

    /**
     * Метод, который обрабатывает запросы клиента.
     * @param socket сокет для подключения клиента.
     * @param serverSocket серверный сокет для принятия клиентских подключений.
     * @throws IOException если возникает ошибка ввода/вывода при работе с сокетами.
     * @throws ClassNotFoundException если класс не был найден при чтении объекта из потока.
     */
    private static void startIteratorLoop(Socket socket, ServerSocket serverSocket) throws IOException, ClassNotFoundException {
        boolean isClientDisconnected = false;
        try {
            ServerSocketIO socketIO = new ServerSocketIO(socket);
            Request request = (Request) socketIO.receive();
            AbstractCommand command = ServerApp.commandManager.initCommand(request);
            ServerApp.logger.info("Сервер получил команду [" + request.getCommandName() + "].");
            if (request.getCommandName().equals("exit")) {
                ServerApp.logger.info("Отключение клиента.");
                try {
                    socket.close();
                } catch (IOException e) {
                    ServerApp.logger.warn("Ошибка при закрытии сокета: " + e.getMessage());
                }
            } else {
                Response response = RequestBuilder.build(command, request);
                socketIO.send(response);
                ServerApp.logger.info("Сервер отправил ответ клиенту.");
            }
        } catch (DisconnectInitException e) {
            socket.close();
            serverSocket.close();
            ServerApp.logger.error("Работа сервера завершена.");
            System.exit(1);
        } catch (IOException e) {
            if (e.getMessage().equals("Connection reset")) {
                isClientDisconnected = true;
                ServerApp.logger.warn("Клиент внезапно отключился."); }
        } finally {
            if (isClientDisconnected) {
                try {
                    socket.close();
                } catch (IOException e) {
                    ServerApp.logger.warn("Ошибка при закрытии сокета: " + e.getMessage());
                }
            }
        }
    }
}