package org.example.collections;

import org.example.file.*;

import java.time.Instant;
import java.util.Vector;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Менеджер коллекции - класс отвечающий за работу с коллекциями
 */
public class CollectionManager {
    private Vector<Route> collection;
    private ZonedDateTime collectionInitialization;
    public CollectionManager(){
        this.collection = new Vector<>();
        String i = Instant.now().toString();
        collectionInitialization = ZonedDateTime.parse(i);
    }

    /**
     * Метод, выводящий основную информацию по используемой коллекции.
     */
    public void info() {
        System.out.println("Коллекция " + collection.getClass().getSimpleName());
        System.out.println("Тип элементов коллекции: " + Route.class.getSimpleName());

        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        System.out.println("Время ининициализации коллекции: " + collectionInitialization.plusHours(3).format(europeanDateFormatter));
        System.out.println("Количество элементов в коллекции: " + collection.size());
    }

    /**
     * Метод, выводящий информацию об элементах коллекции.
     */
    public void show() {
        if (collection.size() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            for (int i=0;i<collection.size();i++) {
                System.out.println(collection.get(i).toString());
            }
        }
    }

    /**
     *  Метод, добавляющий в коллекцию новый элемент.
     * @param id    уникальный идентицикатор элемента коллекции (ключ)
     * @param route элемент коллекции, требующий добавления
     */
    public void insert(Integer id, Route route) {
        if (collection.get(id) == null) {
            collection.add(id, route);
        } else System.out.println("Элемент с данным ключом уже существует");
    }

    /**
     * Метод, удялющий элемент, выбранный по ключу.
     * @param id уникальный идентицикатор элемента коллекции (ключ)
     */
    public void removeKey(Integer id) {
        collection.remove(id);
    }

    /**
     * Метод, удаляющий все элементы коллекции.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Метод, изменяющий поле выбранного по идентификатору элемента коллекции.
     *
     * @param id
     * @param field
     * @param value
     */
    /*public void update(Integer id, String field, String value){
        try{
            switch(field){
                case "name":{
                    if (value.equals("")) throw new NullPointerException();
                    collection.get(id).setName(value);
                    System.out.println("Значение поля было изменено");
                    break;
                }case "coordinates"
            }
        }catch (){

        }
    }*/
}

