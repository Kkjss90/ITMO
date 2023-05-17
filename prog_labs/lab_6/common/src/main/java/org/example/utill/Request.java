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
    private Route routeArgument;

    /**
     * Конструктор класса, принимающий имя команды.
     *
     * @param commandName имя команды
     */
    public Request(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Конструктор класса, принимающий имя команды и числовой аргумент.
     *
     * @param commandName     имя команды
     * @param numericArgument числовой аргумент команды
     */
    public Request(String commandName, Long numericArgument) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
    }

    /**
     * Конструктор класса, принимающий имя команды и объект Route.
     *
     * @param commandName   имя команды
     * @param routeArgument объект организации
     */
    public Request(String commandName, Route routeArgument) {
        this.commandName = commandName;
        this.routeArgument = routeArgument;
    }

    /**
     * Конструктор класса, принимающий имя команды, числовой аргумент и объект Route.
     *
     * @param commandName     имя команды
     * @param numericArgument числовой аргумент команды
     * @param routeArgument   объект организации
     */
    public Request(String commandName, Long numericArgument, Route routeArgument) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.routeArgument = routeArgument;
    }

    /**
     * Получает имя команды.
     *
     * @return имя команды
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Получает числовой аргумент.
     *
     * @return числовой аргумент команды
     */
    public Long getNumericArgument() {
        return numericArgument;
    }

    /**
     * Получает объект организации.
     *
     * @return объект пути
     */
    public Route getRouteArgument() {
        return routeArgument;
    }

    /**
     * Метод getData() возвращает строковое представление данных объекта в виде имени команды и соответствующих аргументов.
     */
    @Override
    public String getData() {
        return "Имя команды для отправки: " + commandName
                + (routeArgument == null ? "" : ("\nИнформация об пути для отправки:\n " + routeArgument))
                + (numericArgument == null ? "" : ("\nЧисловой аргумент для отправки:\n " + numericArgument));
    }

    /**
     * Возвращает строковое представление объекта в формате "Ответ[имя команды]".
     */
    @Override
    public String toString() {
        return "Ответ[" + commandName + "]";
    }
}
