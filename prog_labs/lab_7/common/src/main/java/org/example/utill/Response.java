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
     * Данные о пути, отправляемые в ответ на запрос.
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
     * Конструктор класса Response, принимающий сообщение и информацию об организации для ответа.
     *
     * @param messageToResponse сообщение для ответа
     * @param routeToResponse информация об организации для ответа
     */
    public Response(String messageToResponse, Route routeToResponse) {
        this.messageToResponse = messageToResponse;
        this.routeToResponse = routeToResponse;
    }

    /**
     * Конструктор класса Response, принимающий сообщение и коллекцию организаций для ответа.
     *
     * @param messageToResponse сообщение для ответа.
     * @param collectionToResponse коллекция организаций для ответа
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
     * Конструктор класса Response, принимающий информацию об организации для ответа.
     *
     * @param routeToResponse информация об организации для ответа
     */
    public Response(Route routeToResponse) {
        this.routeToResponse = routeToResponse;
    }

    /**
     * Конструктор класса Response, принимающий коллекцию организаций для ответа.
     *
     * @param collectionToResponse коллекция организаций для ответа
     */
    public Response(ConcurrentLinkedDeque<Route> collectionToResponse) {
        this.collectionToResponse = collectionToResponse;
    }
    public String getMessageToResponse() {
        return messageToResponse;
    }

    /**
     * Метод, возвращающий информацию о пути для ответа.
     *
     * @return информация о пути для ответа
     */
    public Route getRouteToResponse() {
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


