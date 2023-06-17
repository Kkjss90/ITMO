package org.example.utill;

import org.example.data.Route;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * Класс-менеджер коллекции путей.
 * Содержит методы для работы с коллекцией, такие как добавление, удаление,
 * поиск по id, замена по id, сортировка, перемешивание, очистка и т.д.
 * Также возвращает информацию о коллекции.
 */
public class CollectionManager {
    /**
     * Коллекция
     */
    private ConcurrentLinkedDeque<Route> routesCollection = new ConcurrentLinkedDeque<>();

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    static Lock readLock = lock.readLock();
    /**
     * Дата инициализации коллекции
     */
    private Date creationDate;

    /**
     * @return Коллекция.
     */
    public ConcurrentLinkedDeque<Route> getCollection() {
        try{
            readLock.lock();
            return routesCollection;
        }finally {
            readLock.unlock();
        }

    }

    /**
     * Устанавливает коллекцию путей
     * @param routesCollection коллекция путей
     */
    public void setCollection(ConcurrentLinkedDeque<Route> routesCollection) {
        this.routesCollection = routesCollection;
    }

    /**
     * @return тип коллекции.
     */
    public String collectionType() {
        try {
            readLock.lock();
            return routesCollection.getClass().getName();
        }finally {
            readLock.unlock();
        }
    }

    /**
     * @return размер коллекции.
     */
    public int collectionSize() {
        try{
            readLock.lock();
            return routesCollection.size();
        }finally {
            readLock.unlock();
        }

    }

    /**
     * @param id ID пути.
     * @return путь с заданным ID.
     */
    public Route getById(int id) {
        try {
            readLock.lock();
            for (Route route : routesCollection) {
                if (Objects.equals(route.getId(), id)) return route;
            }
            return null;
        }finally {
            readLock.unlock();
        }
    }

    /**
     * Метод удаляет организацию из коллекции по заданному идентификатору.
     * @param id идентификатор организации для удаления
     */
    public void removeById(int id) {
        try {
            writeLock.lock();
            routesCollection.removeIf(p -> Objects.equals(p.getId(), id));
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * Добавляет новый путь в коллекцию.
     * @param route путь для добавления.
     */
    public void addToCollection(Route route) {
        try {
            writeLock.lock();
            routesCollection.add(route);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * Очистка коллекции.
     */
    public void clearCollection() {
        try {
            writeLock.lock();
            routesCollection.clear();
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * Метод возвращает информацию о коллекции организаций.
     * @return строка с типом коллекции, датой инициализации и количеством элементов
     */
    public String getInfo() {
        try {
            readLock.lock();
            return "Тип коллекции: " + routesCollection.getClass()
                    + "\nДата инициализации: " + getCreationDate()
                    + "\nКоличество элементов: " + routesCollection.size();
        }finally {
            readLock.unlock();
        }

    }

    /**
     * Возвращает дату инициализации коллекции
     * @return дата инициализации коллекции
     */
    private Date getCreationDate() {
        try {
            readLock.lock();
            return creationDate;
        }finally {
            readLock.unlock();
        }

    }

    /**
     * Удаляет путь из коллекции.
     * @param route путь для удаления.
     */
    public void removeFromCollection(Route route) {
        try {
            writeLock.lock();
            routesCollection.remove(route);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * Удаляет путь, значение которого больше выбранного.
     */
    public void removeGreater(long distance) {
        try {
            writeLock.lock();
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
        }finally {
            writeLock.unlock();
        }
    }
    /**
     * @param routeToFind путь, значение которого будет найдено.
     * @return путь.
     */
    public Route getByValue(Route routeToFind) {
        try {
            readLock.lock();
            for (Route route : routesCollection) {
                if (route.equals(routeToFind)) return route;
            }
            return null;
        }finally {
            readLock.unlock();
        }
    }
    /**
     * Удаляет путь, значение которого меньше выбранного.
     */
    public void removeLower(long distance) {
        try {
            writeLock.lock();
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
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * Возвращает пути с заданной дистанцией.
     * @param distance дистанция
     * @return массив путей с заданной дистанцией
     */
    public Route[] getByDistance(long distance) {
        try {
            readLock.lock();
            Route[] routes = new Route[0];
            for (Route route : routesCollection) {
                if (Objects.equals(route.getDistance(), distance)) {
                    Arrays.stream(routes).toArray();
                    return routes;
                }
            }
            return null;
        }finally {
            readLock.unlock();
        }
    }
    /**
     * @param distanceToFilter дистанция, по значению которой происходит фильтрация.
     * @return Информация о пути.
     */
    public String distanceFilteredInfo(long distanceToFilter) {
        try {
            readLock.lock();
            String info = "";
            for (Route route : routesCollection) {
                if (route.getDistance() == distanceToFilter) {
                    info += route + "\n\n";
                }
            }
            return info.trim();
        }finally {
            readLock.unlock();
        }
    }

    /**
     * Перемешивает элементы коллекции организаций в случайном порядке.
     */
    public void shuffle(){
        try {
            writeLock.lock();
            Collections.shuffle((List<?>) routesCollection);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * Возвращает все объекты коллекции.
     * Если коллекция пуста, возвращается строка "Коллекция пуста".
     * @return все объекты коллекции.
     */
    public String show() {
        try {
            readLock.lock();
            if (routesCollection.isEmpty()) return "Коллекция пуста";
            return routesCollection.stream()
                    .map(route -> route.toString() + "\n")
                    .collect(Collectors.joining());
        }finally {
            readLock.unlock();
        }
    }

    /**
     * Выводит количество элементов с указанной дистанцией.
     * @return массив дистанция и количество элементов с заданной дистанцией.
     */
    public Map<Long, Integer> countByDistance(){
        try {
            writeLock.lock();
            HashMap<Long, Integer> map = new HashMap<>();
            routesCollection.stream()
                    .filter((route -> route.getDistance() != 0))
                    .forEach((route) -> {
                        long distance = route.getDistance();
                        if (map.containsKey(distance)) {
                            Integer q = map.get(distance);
                            map.replace(distance, q + 1);
                        } else {
                            map.put(distance, 1);
                        }
                    });
            return map;
        }finally {
            writeLock.unlock();
        }
    }
    /**
     * Возвращает коллекцию организаций с указанными идентификаторами.
     * @param ids список идентификаторов организаций
     * @return коллекция организаций с указанными идентификаторами
     */
    public ConcurrentLinkedDeque<Route> getUsersElements(List<Integer> ids) {
        try {
            readLock.lock();
            return routesCollection.stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toCollection(ConcurrentLinkedDeque::new));
        }finally {
            readLock.unlock();
        }
    }

    /**
     * Возвращает коллекцию организаций, которые не содержатся в указанном списке идентификаторов.
     * @param ids список идентификаторов организаций
     * @return коллекция организаций, которые не содержатся в указанном списке идентификаторов
     */
    public ConcurrentLinkedDeque<Route> getAlienElements(List<Integer> ids) {
        try {
            readLock.lock();
            return routesCollection.stream().filter(p -> !ids.contains(p.getId())).collect(Collectors.toCollection(ConcurrentLinkedDeque::new));
        }finally {
            readLock.unlock();
        }
    }
    @Override
    public String toString() {
        if (routesCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Route route : routesCollection) {
            info += route;
            if (route != routesCollection.getLast()) info += "\n\n";
        }
        return info;
    }

}