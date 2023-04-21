package org.example.utility;

import org.example.data.Route;

import java.util.*;

/**
 * Класс, оперирующий коллекцией.
 */
public class CollectionManager {
    /**
     * Коллекция
     */
    private Vector<Route> routesCollection = new Vector<>();
    /**
     * Дата последней инициализации
     */
    private Date lastInitTime;
    /**
     * Дата последней загрузки
     */
    private Date lastSaveTime;
    /**
     * Менеджер файлов
     */
    private FileManager fileManager;

    /**
     * Конструктор класса
     * @param fileManager менеджер файлов
     */

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    /**
     * @return Коллекция.
     */
    public Vector<Route> getCollection() {
        return routesCollection;
    }

    /**
     * @return Дата последней инициализации.
     */
    public Date getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Дата последней загрузки.
     */
    public Date getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return тип коллекции.
     */
    public String collectionType() {
        return routesCollection.getClass().getName();
    }

    /**
     * @return размер коллекции.
     */
    public int collectionSize() {
        return routesCollection.size();
    }

    /**
     * @return первый элемент коллекции.
     */
    public Route getFirst() {
        if (routesCollection.isEmpty()) return null;
        return routesCollection.firstElement();
    }

    /**
     * @return последний элемент коллекции.
     */
    public Route getLast() {
        if (routesCollection.isEmpty()) return null;
        return routesCollection.lastElement();
    }

    /**
     * @param id ID пути.
     * @return путь с заданным ID.
     */
    public Route getById(int id) {
        for (Route route : routesCollection) {
            if(Objects.equals(route.getId(), id)) return route;
        }
        return null;
    }
    /**
     * Выгрузка коллекции из файла.
     */
    private void loadCollection() {
        routesCollection = fileManager.readCollection();
        lastInitTime = new Date();
    }

    /**
     * Генерация уникального ID.
     * @return ID.
     */
    public int generateNextId() {
        if (routesCollection.isEmpty()) return 1;
        return routesCollection.lastElement().getId() + 1;
    }

    /**
     * Добавляет новый путь в коллекцию.
     * @param route путь для добавления.
     */
    public void addToCollection(Route route) {
        routesCollection.add(route);
    }

    /**
     * Очистка коллекции.
     */
    public void clearCollection() {
        routesCollection.clear();
    }

    /**
     * Удаляет путь из коллекции.
     * @param route путь для удаления.
     */
    public void removeFromCollection(Route route) {
        routesCollection.remove(route);
    }

    /**
     * Сохраняет коллекцию в файл.
     */
    public void saveCollection() {
        fileManager.writeCollection(routesCollection);
        lastSaveTime = new Date();
    }

    /**
     * Удаляет путь, значение которого больше выбранного.
     */
    public void removeGreater(long distance) {
        Iterator<Route> iterator = routesCollection.iterator();
        while (iterator.hasNext()) {
            Route route = iterator.next();
            {
                if (route.getDistance() > distance) {
                    iterator.remove();
                    System.out.println("Элемент удален из коллекции: " + route.getName());
                } else if (!iterator.hasNext()) {
                    System.out.println("Нет элементов с такой же дистанцией");
                }
            }
        }
    }
    /**
     * @param routeToFind путь, значение которого будет найдено.
     * @return путь.
     */
    public Route getByValue(Route routeToFind) {
        for (Route route : routesCollection) {
            if (route.equals(routeToFind)) return route;
        }
        return null;
    }
    /**
     * Удаляет путь, значение которого меньше выбранного.
     */
    public void removeLower(long distance) {
        Iterator<Route> iterator = routesCollection.iterator();
        while (iterator.hasNext()) {
            Route route = iterator.next();
            {
                if (route.getDistance() < distance) {
                    iterator.remove();
                    System.out.println("Элемент удален из коллекции: " + route.getName());
                } else if (!iterator.hasNext()) {
                    System.out.println("Нет элементов с такой же дистанцией");
                }
            }
        }
    }

    /**
     * Возвращает пути с заданной дистанцией.
     * @param distance дистанция
     * @return массив путей с заданной дистанцией
     */
    public Route[] getByDistance(long distance) {
        Route[] routes = new Route[0];
        for (Route route : routesCollection) {
            if(Objects.equals(route.getDistance(), distance)){
                Arrays.stream(routes).toArray();
                return routes;
            }
        }
        return null;
    }
    /**
     * @param distanceToFilter дистанция, по значению которой происходит фильтрация.
     * @return Информация о пути.
     */
    public String distanceFilteredInfo(long distanceToFilter) {
        String info = "";
        for (Route route : routesCollection) {
            if (route.getDistance() == distanceToFilter) {
                info += route + "\n\n";
            }
        }
        return info.trim();
    }

    /**
     * Перемешивает элементы коллекции организаций в случайном порядке.
     */
    public void shuffle(){
        Collections.shuffle(routesCollection);
    }

    /**
     * Выводит количество элементов с указанной дистанцией.
     * @return массив дистанция и количество элементов с заданной дистанцией.
     */
    public Map<Long, Integer> countByDistance(){
        HashMap<Long,Integer> map = new HashMap<>();
        routesCollection.stream()
                .filter((route -> route.getDistance()!=0))
                .forEach((route)->{
                    long distance = route.getDistance();
                    if (map.containsKey(distance)){
                        Integer q = map.get(distance);
                        map.replace(distance, q+1);
                    } else{
                        map.put(distance, 1);
                    }
                });
        return map;
    }
    @Override
    public String toString() {
        if (routesCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Route route : routesCollection) {
            info += route;
            if (route != routesCollection.lastElement()) info += "\n\n";
        }
        return info;
    }

}