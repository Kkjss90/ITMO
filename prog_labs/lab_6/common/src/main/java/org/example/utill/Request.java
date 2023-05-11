package org.example.utill;

import org.example.data.Route;
import org.example.interfaces.Data;

import java.io.Serializable;

/**
 * Класс, представляющий объект-запрос.
 * Реализует интерфейс Serializable для возможности сериализации объектов.
 * Реализует интерфейс Data для отправки данных по сети.
 * Содержит информацию о команде и параметрах команды.
 */
public class Request implements Serializable, Data {
    /**
     * Имя команды.
     */
    private final String commandName;

    /**
     * Числовой аргумент команды.
     */
    private Long numericArgument;

    /**
     * Аргумент команды - объект Route.
     */
    private Route route;

    /**
     * Конструктор класса, принимающий имя команды.
     * @param commandName имя команды
     */
    public Request(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Конструктор класса, принимающий имя команды и числовой аргумент.
     * @param commandName имя команды
     * @param numericArgument числовой аргумент команды
     */
    public Request(String commandName, Long numericArgument) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
    }

    /**
     * Конструктор класса, принимающий имя команды и объект Organization.
     * @param commandName имя команды
     * @param route объект организации
     */
    public Request(String commandName, Route route) {
        this.commandName = commandName;
        this.route = route;
    }

    /**
     * Конструктор класса, принимающий имя команды, числовой аргумент и объект Organization.
     * @param commandName имя команды
     * @param numericArgument числовой аргумент команды
     * @param route объект организации
     */
    public Request(String commandName, Long numericArgument, Route route) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.route = route;
    }


    @Override
    public String getData() {
        return null;
    }
}
