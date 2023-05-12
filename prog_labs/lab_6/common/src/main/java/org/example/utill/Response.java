package org.example.utill;

import org.example.data.Route;
import org.example.interfaces.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Класс Response - класс, содержащий информацию для ответа на запрос.
 */
public class Response implements Serializable, Data {

    /**
     * Сообщение, отправляемое в ответ на запрос.
     */
    private String messageToResponse;
    /**
     * Данные об пути, отправляемые в ответ на запрос.
     */
    private Route routeToResponse;
    /**
     * Данные коллекции, отправляемые в ответ на запрос.
     */
    private List<Route> collectionToResponse;

    /**
     * Конструктор класса Response, принимающий сообщение для ответа.
     *
     * @param messageToResponse сообщение для ответа
     */
    public Response(String messageToResponse) {
        this.messageToResponse = messageToResponse;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и информацию об пути для ответа.
     *
     * @param messageToResponse сообщение для ответа
     * @param routeToResponse   информация об пути для ответа
     */
    public Response(String messageToResponse, Route routeToResponse) {
        this.messageToResponse = messageToResponse;
        this.routeToResponse = routeToResponse;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и коллекцию путей для ответа.
     *
     * @param messageToResponse    сообщение для ответа.
     * @param collectionToResponse коллекция путей для ответа
     */
    public Response(String messageToResponse, List<Route> collectionToResponse) {
        this.messageToResponse = messageToResponse;
        this.collectionToResponse = collectionToResponse;
    }

    /**
     * Конструктор класса Response, принимающий информацию об пути для ответа.
     *
     * @param routeToResponse информация об пути для ответа
     */
    public Response(Route routeToResponse) {
        this.routeToResponse = routeToResponse;
    }

    /**
     * Конструктор класса Response, принимающий коллекцию путей для ответа.
     *
     * @param collectionToResponse коллекция путей для ответа
     */
    public Response(List<Route> collectionToResponse) {
        this.collectionToResponse = collectionToResponse;
    }

    /**
     * Метод, возвращающий сообщение для ответа.
     *
     * @return сообщение для ответа
     */
    public String getMessageToResponse() {
        return messageToResponse;
    }

    /**
     * Метод, возвращающий информацию об пути для ответа.
     *
     * @return информация об пути для ответа
     */
    public Route getRouteToResponse() {
        return routeToResponse;
    }

    /**
     * Метод, возвращающий коллекцию путей для ответа.
     *
     * @return коллекция путей для ответа
     */
    public List<Route> getCollectionToResponse() {
        return collectionToResponse;
    }

    /**
     * Метод, возвращающий информацию для отправки.
     *
     * @return информация для отправки
     */
    @Override
    public String getData() {
        return (messageToResponse == null ? "" : (getMessageToResponse()))
                + (routeToResponse == null ? "" : ("\nДанные пути:\n" +  getRouteToResponse().toString()))
                + (collectionToResponse == null ? "" : ("\nКоллекция:\n" + getCollectionToResponse()));
    }

    /**
     * Представляет ответ, полученный от сервера, в формате, удобном для чтения.
     */
    @Override
    public String toString() {
        return "Ответ[" + messageToResponse + "]";
    }
}


