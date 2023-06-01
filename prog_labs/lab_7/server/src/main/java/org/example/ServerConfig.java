package org.example;

import org.example.commands.*;
import org.example.dataBase.DBManager;
import org.example.dataBase.UsersManager;
import org.example.exceptions.DatabaseException;
import org.example.utill.CollectionManager;
import org.example.utill.CommandManager;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


/**
 Класс, представляющий конфигурацию сервера.
 */
public class ServerConfig {

    /**
     Переменная, указывающая на то, работает ли сервер.
     */
    public static boolean isRunning = true;

    /**
     Объект класса Scanner для считывания ввода пользователя.
     */
    public static final Scanner scanner = new Scanner(System.in);

    /**
     Объект класса CollectionManager для работы с коллекцией элементов.
     */
    public static CollectionManager collectionManager = new CollectionManager();

    /**
     Порт, используемый сервером.
     */
    public static int PORT = 65435;

    /**
     Объект класса DBManager для работы с базой данных.
     */
    public static DBManager dbManager;

    /**
     Объект класса UsersManager для работы с пользователями.
     */
    public static UsersManager usersManager;

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
            ServerConfig.logger.addAppender(fileAppender);
        } catch (SecurityException e) {
            ServerConfig.logger.warn("Отказано в доступе при открытии файла журнала: " + e.getMessage());
        } catch (Exception e) {
            ServerConfig.logger.warn("Произошла ошибка при открытии обработчика файла журнала: " + e.getMessage());
        }
    }

    static {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class); // Создание объекта ApplicationContext для конфигурации приложения
            dbManager = context.getBean(DBManager.class);
            usersManager = context.getBean(UsersManager.class); // Получение объектов DBManager и UsersManager из контекста приложения
            collectionManager.setCollection(dbManager.loadCollection()); // Загрузка коллекции элементов из базы данных
        } catch (DatabaseException e) {
            logger.warn(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Объект класса CommandManager для работы с командами.
     */
    public static CommandManager commandManager = new CommandManager();

    /**
     * Метод для изменения переменной isRunning, указывающего на то, работает ли сервер.
     */
    public static void toggleStatus() {
        isRunning = !isRunning;
    }
}