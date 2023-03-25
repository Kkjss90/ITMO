package org.example.collections;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Путь - класс, который содержит объекты(значения) коллекции.
 */

public class Route {
    private static int uid = 1;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Position to; //Поле может быть null
    private float distance; //Значение поля должно быть больше 1
    public Route(String name, Coordinates coordinates, java.time.LocalDate creationDate, Location from, Position to, float distance){
        this.id = (long) uid++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
    public Route(Long id, String name, Coordinates coordinates, java.time.LocalDate creationDate, Location from, Position to, float distance){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий координаты объекта класса
     *
     * @return coordinates - координаты объекта класса
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public String getFormattedCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return creationDate.format(formatter);
    }

    public Location getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public float getDistance() {
        return distance;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCoordinateX(int x){
        this.coordinates.getCoordinates().setX(x);
    }
    public void setCoordinateY(int y){
        this.coordinates.getCoordinates().setY(y);
    }
    public void setFrom(Location from){
        this.from = from;
    }
    public void setTo(Position to){
        this.to = to;
    }
    public void setDistance(float distance){
        this.distance = distance;
    }
}
