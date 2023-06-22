package org.example.utill;

import org.example.data.Route;
import org.example.interfaces.Data;

import java.io.Serializable;


public class Request implements Serializable, Data {

    private String commandName;
    private Long numericArgument;
    private Route routeArgument;

    private String login;

    private String password;

    private final RequestType type;

    public Request(String commandName, RequestType type) {
        this.commandName = commandName;
        this.type = type;
    }

    public Request(String commandName, Long numericArgument, RequestType type) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.type = type;
    }

    public Request(String commandName, Route routeArgument, RequestType type) {
        this.commandName = commandName;
        this.routeArgument = routeArgument;
        this.type = type;
    }

    public Request(String commandName, Long numericArgument, Route routeArgument, RequestType type) {
        this.commandName = commandName;
        this.numericArgument = numericArgument;
        this.routeArgument = routeArgument;
        this.type = type;
    }

    public Request(String login, String password, RequestType type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public String getCommandName() {
        return commandName;
    }

    public Long getNumericArgument() {
        return numericArgument;
    }

    public Route getRouteArgument() {
        return routeArgument;
    }
    public RequestType getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Метод getData() возвращает строковое представление данных объекта в виде имени команды и соответствующих аргументов.
     */
    @Override
    public String getData(){
        return "Имя команды для отправки: " + commandName
                + (routeArgument == null ? "" : ("\nИнформация об организации для отправки:\n " + routeArgument))
                + (numericArgument == null ? "" : ("\nЧисловой аргумент для отправки:\n " + numericArgument));
    }

    /**
     * Возвращает строковое представление объекта в формате "Ответ[имя команды]".
     */
    @Override
    public String toString() {
        return "Ответ[" + commandName + "]" ;
    }
}