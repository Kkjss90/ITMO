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
    private String commandName;

    /**
     * Числовой аргумент команды.
     */
    private Long numericArgument;

    /**
     * Аргумент команды - объект Route.
     */
    private Route routeArgument;

    private final RequestType type;

    private String login;

    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RequestType getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Request(String login, String password, RequestType type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }
    /**
     * Конструктор класса, принимающий имя команды.
     *
     * @param commandName имя команды
     * @param type
     */
    public Request(String commandName, RequestType type) {
        this.commandName = commandName;
        this.type = type;
    }

    /**
     * Конструктор класса, принимающий имя команды и числовой аргумент.
     *
     * @param commandName     имя команды
     * @param numericArgument числовой аргумент команды
     * @param type
     */
    public Request(String commandName, Long numericArgument, RequestType type) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.type = type;
    }

    /**
     * Конструктор класса, принимающий имя команды и объект Route.
     *
     * @param commandName   имя команды
     * @param routeArgument объект организации
     * @param type
     */
    public Request(String commandName, Route routeArgument, RequestType type) {
        this.commandName = commandName;
        this.routeArgument = routeArgument;
        this.type = type;
    }

    /**
     * Конструктор класса, принимающий имя команды, числовой аргумент и объект Route.
     *
     * @param commandName     имя команды
     * @param numericArgument числовой аргумент команды
     * @param routeArgument   объект организации
     * @param type
     */
    public Request(String commandName, Long numericArgument, Route routeArgument, RequestType type) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.routeArgument = routeArgument;
        this.type = type;
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
