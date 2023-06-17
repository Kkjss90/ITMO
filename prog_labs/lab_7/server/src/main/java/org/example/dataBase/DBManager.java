package org.example.dataBase;

import org.example.data.Coordinates;
import org.example.data.Location;
import org.example.data.Position;
import org.example.data.Route;
import org.example.exceptions.DatabaseException;
import org.example.interfaces.DBConnectable;
import org.example.utill.Encryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     * Метод для загрузки коллекции путей из базы данных.
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
                        collectionSet.getInt("coordinates_x"),
                        collectionSet.getFloat("coordinates_y")
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
    /**
     * Метод для добавления нового пути в базу данных.
     * @param route путь, который нужно добавить.
     * @param username имя пользователя, которому принадлежит путь.
     * @return идентификатор нового пути в базе данных.
     * @throws DatabaseException если произошла ошибка при обращении к базе данных.
     */
    public Long addElement(Route route, String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String addElementQuery = "INSERT INTO routes "
                    + "(creation_date, name, coordinates_x, coordinates_y, from_location_x, from_location_y, from_location_z, from_location_name, to_position_x, to_position_y, to_position_z, distance, owner_id) "
                    + "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, id "
                    + "FROM users "
                    + "WHERE users.login = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(addElementQuery,
                    Statement.RETURN_GENERATED_KEYS);

            Coordinates coordinates = route.getCoordinates();
            Location location = route.getFrom();
            Position position = route.getTo();
            preparedStatement.setDate(1, new java.sql.Date(route.getCreationDate().getTime()));
            preparedStatement.setString(2, route.getName());
            preparedStatement.setInt(3, coordinates.getX());
            preparedStatement.setFloat(4, coordinates.getY());
            preparedStatement.setDouble(5, location.getX());
            preparedStatement.setFloat(6, location.getY());
            preparedStatement.setInt(7, location.getZ());
            preparedStatement.setString(8, location.getName());
            preparedStatement.setInt(9,position.getX());
            preparedStatement.setInt(10, position.getY());
            preparedStatement.setDouble(11, position.getZ());
            preparedStatement.setLong(12, route.getDistance());
            preparedStatement.setString(13, username);

            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            result.next();

            return result.getLong(1);
        });
    }
    /**
     * Метод для проверки существования пути с заданным идентификатором в базе данных.
     * @param id идентификатор пути
     * @return true, если путь существует в базе данных, иначе - false
     * @throws DatabaseException если возникла ошибка при обращении к базе данных
     */
    public boolean checkRouteExistence(int id) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String existenceQuery = "SELECT COUNT (*) "
                    + "FROM routes "
                    + "WHERE routes.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(existenceQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getInt("count") > 0;
        });
    }
    /**
     * Метод для удаления пути с заданным идентификатором, принадлежащей заданному пользователю, из базы данных.
     * @param id идентификатор удаляемого пути
     * @param username логин пользователя, которому принадлежит удаляемая организация
     * @return true, если путь был успешно удален, иначе - false
     * @throws DatabaseException если возникла ошибка при обращении к базе данных
     */
    public boolean removeById(int id, String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String removeQuery = "DELETE FROM routes "
                    + "USING users "
                    + "WHERE routes.id = ? "
                    + "AND routes.owner_id = users.id AND users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);

            int deletedBands = preparedStatement.executeUpdate();
            return deletedBands > 0;
        });
    }
    public boolean updateById(Route route, int id, String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            connection.createStatement().execute("BEGIN TRANSACTION;");
            String updateQuery = "UPDATE routes "
                    + "SET creation_date = ?, "
                    + "name = ?, "
                    + "coordinates_x = ?, "
                    + "coordinates_y = ?, "
                    + "from_location_x = ?, "
                    + "from_location_y = ?, "
                    + "from_location_z = ?, "
                    + "from_location_name = ?, "
                    + "to_position_x = ? "
                    + "to_position_y = ? "
                    + "to_position_z = ? "
                    + "distance = ? "
                    + "FROM users "
                    + "WHERE routes.id = ? "
                    + "AND routes.owner_id = users.id AND users.login = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            Coordinates coordinates = route.getCoordinates();
            Location location = route.getFrom();
            Position position = route.getTo();
            preparedStatement.setDate(1, new java.sql.Date(route.getCreationDate().getTime()));
            preparedStatement.setString(2, route.getName());
            preparedStatement.setInt(3, coordinates.getX());
            preparedStatement.setFloat(4, coordinates.getY());
            preparedStatement.setDouble(5, location.getX());
            preparedStatement.setFloat(6, location.getY());
            preparedStatement.setInt(7, location.getZ());
            preparedStatement.setString(8, location.getName());
            preparedStatement.setInt(9,position.getX());
            preparedStatement.setInt(10, position.getY());
            preparedStatement.setDouble(11, position.getZ());
            preparedStatement.setLong(12, route.getDistance());
            preparedStatement.setString(13, username);

            int updatedRows = preparedStatement.executeUpdate();
            connection.createStatement().execute("COMMIT;");

            return updatedRows > 0;
        });
    }
    /**
     * Очищает базу данных от путей, которые принадлежат указанному пользователю.
     *
     * @param username логин пользователя, пути которого нужно удалить.
     * @return список идентификаторов удаленных путей.
     * @throws DatabaseException если произошла ошибка при работе с базой данных.
     */
    public List<Integer> clear(String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String clearQuery = "DELETE FROM routes "
                    + "USING users "
                    + "WHERE routes.owner_id = users.id AND users.login = ? "
                    + "RETURNING routes.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(clearQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> resultingList = new ArrayList<>();
            while (resultSet.next()) {
                resultingList.add(resultSet.getInt("id"));
            }
            return resultingList;
        });
    }

    /**
     * Добавляет нового пользователя в базу данных.
     * @param username логин пользователя.
     * @param password пароль пользователя.
     * @throws DatabaseException если произошла ошибка при работе с базой данных.
     */
    public void addUser(String username, String password) throws DatabaseException {
        dbConnector.handleQuery((Connection connection) -> {
            String addUserQuery = "INSERT INTO users (login, password) "
                    + "VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, Encryptor.encryptString(password));

            preparedStatement.executeUpdate();
        });
    }

    /**
     * Получает пароль пользователя с указанным логином.
     * @param username логин пользователя.
     * @return пароль пользователя или null, если пользователь не найден.
     * @throws DatabaseException если произошла ошибка при работе с базой данных.
     */
    public String getPassword(String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String getPasswordQuery = "SELECT (password) "
                    + "FROM users "
                    + "WHERE users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(getPasswordQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
            return null;
        });
    }
    /**
     * Проверяет существование пользователя с указанным логином.
     * @param username логин пользователя.
     * @return true, если пользователь существует, иначе false.
     * @throws DatabaseException если произошла ошибка при работе с базой данных.
     */
    public boolean checkUsersExistence(String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String existenceQuery = "SELECT COUNT (*) "
                    + "FROM users "
                    + "WHERE users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(existenceQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getInt("count") > 0;
        });
    }

    /**
     * Метод для получения списка id путей пользователя
     * @param username логин пользователя
     * @return список id пути пользователя
     * @throws DatabaseException если произошла ошибка при работе с базой данных
     */
    public List<Integer> getIdsOfUsersElements(String username) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) -> {
            String getIdsQuery = "SELECT routes.id FROM routes, users "
                    + "WHERE routes.owner_id = users.id AND users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(getIdsQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> resultingList = new ArrayList<>();
            while (resultSet.next()) {
                resultingList.add(resultSet.getInt("id"));
            }

            return resultingList;
        });
    }
    /**
     * Метод для проверки правильности введенных пользователем данных
     * @param username логин пользователя
     * @param password пароль пользователя
     * @return true, если данные верны, false в противном случае
     * @throws DatabaseException если произошла ошибка при работе с базой данных
     */
    public boolean validateUser(String username, String password) throws DatabaseException {
        return dbConnector.handleQuery((Connection connection) ->
                Encryptor.encryptString(password).equals(getPassword(username)));
    }
}
