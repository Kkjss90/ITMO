package org.example.dataBase;

import org.example.data.Coordinates;
import org.example.data.Location;
import org.example.data.Position;
import org.example.data.Route;
import org.example.exceptions.DatabaseException;
import org.example.interfaces.DBConnectable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * DBManager - класс, предоставляющий методы для работы с базой данных.
 */
public class DBManager {
    /**
     * Класс, предоставляющий подключение к базе данных.
     */
    private final DBConnectable dbConnector;

    /**
     * Конструктор класса DBManager.
     *
     * @param dbConnector объект, предоставляющий подключение к базе данных.
     */
    public DBManager(DBConnectable dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Метод для загрузки коллекции организаций из базы данных.
     *
     * @return объект ConcurrentLinkedDeque с загруженной коллекцией.
     * @throws DatabaseException если произошла ошибка при обращении к базе данных.
     */
    public ConcurrentLinkedDeque<Route> loadCollection() throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String selectCollectionQuery = "SELECT * FROM routes";
            Statement statement = connection.createStatement();
            ResultSet collectionSet = statement.executeQuery(selectCollectionQuery);
            ConcurrentLinkedDeque<Route> resultDeque = new ConcurrentLinkedDeque<>();
            while (collectionSet.next()) {
                Coordinates coordinates = new Coordinates(
                        collectionSet.getInt("x"),
                        collectionSet.getFloat("y")
                );
                Location location = new Location(
                        collectionSet.getDouble("from_location_x"),
                        collectionSet.getFloat("from_location_y"),
                        collectionSet.getInt("from_location_z"),
                        collectionSet.getString("from_location_name")
                );
                Position position = new Position(
                        collectionSet.getInt("to_position_x"),
                        collectionSet.getInt("to_position_y"),
                        collectionSet.getDouble("to_position_z")
                );
                Route route = new Route(
                        collectionSet.getInt("id"),
                        collectionSet.getString("name"),
                        coordinates,
                        Date.from(collectionSet.getDate("creation_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        location,
                        position,
                        collectionSet.getLong("distance")
                );
                resultDeque.add(route);
            }
            return resultDeque;
        });
    }
}
