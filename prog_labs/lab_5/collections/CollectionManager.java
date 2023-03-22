package managers;

import file.*;
import java.util.Vector;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Менеджер коллекции - класс отвечающий за работу с коллекциями
 */
public class CollectionManager {
    private Vector<Route> collection = new Vector<Route>();
    private ZonedDateTime collectionInitialization;
    public CollectionManager(){
        this.collection = new Vector<Route>;
        String i = Instant.now().toString();
        collectionInitialization = ZonedDateTime.parse(i);
    }
    public void info() {
        System.out.println("Коллекция " + collection.getClass().getSimpleName());
        System.out.println("Тип элементов коллекции: " + Route.class.getSimpleName());

        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        System.out.println("Время ининициализации коллекции: " + collectionInitialization.plusHours(3).format(europeanDateFormatter));
        System.out.println("Количество элементов в коллекции: " + collection.size());
    }
    public void show() {
        if (collection.size() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            for (Map.Entry<Route> entry : collection.entrySet()) {
                System.out.println(entry.getValue().toString());
            }
        }
    }
    public void insert(Integer id, Route route) {
        if (collection.get(id) == null) {
            collection.put(id, route);
        } else System.out.println("Элемент с данным ключом уже существует");
    }
    public void removeKey(Integer id) {
        collection.remove(id);
    }
    public void clear() {
        collection.clear();
    }

}

