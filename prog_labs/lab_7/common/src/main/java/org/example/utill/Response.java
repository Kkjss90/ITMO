package org.example.utill;


import org.example.data.Route;
import org.example.interfaces.Data;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Класс Response - класс, содержащий информацию для ответа на запрос.
 */
public class Response implements Serializable, Data {

    /**
     * Сообщение, отправляемое в ответ на запрос.
     */
    private String messageToResponse;

    /**
     * Данные об организации, отправляемые в ответ на запрос.
     */
    private Route routeToResponse;

    /**
     * Данные коллекции, отправляемые в ответ на запрос.
     */
    private ConcurrentLinkedDeque<Route> collectionToResponse;

    /**
     * Коллекция элементов, не принадлежащих клиенту
     */
    private ConcurrentLinkedDeque<Route> alienElements;

    /**
     * Список дополнительной информации, содержащейся в ответе сервера
     */
    private List<String> info;

    /**
     * Конструктор класса Response, принимающий сообщение для ответа.
     *
     * @param messageToResponse сообщение для ответа
     */
    public Response(String messageToResponse) {
        this.messageToResponse = messageToResponse;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и информацию о пути для ответа.
     *
     * @param messageToResponse сообщение для ответа
     * @param routeToResponse информация о пути для ответа
     */
    public Response(String messageToResponse, Route routeToResponse) {
        this.messageToResponse = messageToResponse;
        this.routeToResponse = routeToResponse;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и коллекцию путей для ответа.
     *
     * @param messageToResponse сообщение для ответа.
     * @param collectionToResponse коллекция путей для ответа
     */
    public Response(String messageToResponse, ConcurrentLinkedDeque<Route> collectionToResponse) {
        this.messageToResponse = messageToResponse;
        this.collectionToResponse = collectionToResponse;
    }

    /**
     * Конструктор класса для создания ответа сервера, содержащего только сообщение
     * @param messageToResponse сообщение, которое будет отправлено клиенту
     * @param info список дополнительной информации для клиента
     */
    public Response(String messageToResponse, List<String> info) {
        this.messageToResponse = messageToResponse;
        this.info = info;
    }

    /**
     * Конструктор класса для создания ответа сервера, содержащего сообщение и коллекцию элементов
     * @param messageToResponse сообщение, которое будет отправлено клиенту
     * @param usersElements коллекция элементов для отправки клиенту
     * @param alienElements коллекция элементов, не принадлежащих клиенту
     */
    public Response(String messageToResponse, ConcurrentLinkedDeque<Route> usersElements, ConcurrentLinkedDeque<Route> alienElements) {
        this.messageToResponse = messageToResponse;
        collectionToResponse = usersElements;
        this.alienElements = alienElements;
    }

    /**
     * Конструктор класса Response, принимающий информацию о пути для ответа.
     *
     * @param routeToResponse информация о пути для ответа
     */
    public Response(Route routeToResponse) {
        this.routeToResponse = routeToResponse;
    }

    /**
     * Конструктор класса Response, принимающий коллекцию путей для ответа.
     *
     * @param collectionToResponse коллекция путей для ответа
     */
    public Response(ConcurrentLinkedDeque<Route> collectionToResponse) {
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
     * Метод, возвращающий информацию о пути для ответа.
     *
     * @return информация о пути для ответа
     */
    public Route getOrganizationToResponse() {
        return routeToResponse;
    }

    /**
     * Метод, возвращающий коллекцию путей для ответа.
     *
     * @return коллекция путей для ответа
     */
    public ConcurrentLinkedDeque<Route> getCollectionToResponse() {
        return collectionToResponse;
    }

    /**
     * Возвращает список дополнительной информации, содержащейся в ответе сервера
     * @return список дополнительной информации
     */
    public List<String> getInfo() {
        return info;
    }

    /**
     * Возвращает коллекцию элементов, не принадлежащих клиенту
     * @return коллекция элементов
     */
    public ConcurrentLinkedDeque<Route> getAlienElements() {
        return alienElements;
    }


    /**
     * Метод, возвращающий информацию для отправки.
     *
     * @return информация для отправки
     */
    @Override
    public String getData() {
        return (messageToResponse == null ? "" : (getMessageToResponse()))
                + (routeToResponse == null ? "" : ("\nДанные организации:\n" +  getOrganizationToResponse().toString()))
                + (collectionToResponse == null ? "" : ("\nКоллекция:\n" + getCollectionToResponse()))
                + (alienElements == null ? "" :("\nКоллекции других пользователей:\n" +
                (getAlienElements().isEmpty() ? "В коллекциях других пользователей нет элементов" : getAlienElements())));
    }

    /**
     * Представляет ответ, полученный от сервера, в формате, удобном для чтения.
     */
    @Override
    public String toString() {
        return "Ответ[" + messageToResponse + "]";
    }
}