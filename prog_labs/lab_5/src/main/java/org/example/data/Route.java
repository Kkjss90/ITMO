package org.example.data;

import java.util.Date;
import java.util.Objects;

public class Route implements Comparable<Route>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Position to; //Поле может быть null
    private long distance; //Значение поля должно быть больше 1

    public Route(int id, String name, Coordinates coordinates, java.util.Date creationDate, Location from, Position to, long distance){
        this.id= id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return name.equals(route.getName()) && coordinates.equals(route.getCoordinates()) && from.equals(route.getFrom()) && to.equals(route.getTo()) && distance == route.getDistance();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, from, to, distance);
    }

    @Override
    public String toString() {
        return "Путь" + "\n" +
                "id = " + id + "\n" +
                "имя = " + name + '\n' +
                "координаты = " + coordinates + "\n" +
                "дата создания = " + creationDate + "\n" +
                "откуда = " + from + "\n" +
                "куда = " + to + "\n" +
                "дистанция = " + distance + "\n";
    }

    @Override
    public int compareTo(Route o) {
        if (this.getDistance() > o.getDistance()){
            return 1;
        }
        else if (this.getDistance() < o.getDistance()){
            return -1;
        }else{
            return 0;
        }
    }
}
