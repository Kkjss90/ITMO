package org.example.dataBase;

import org.example.ServerConfig;
import org.example.exceptions.DatabaseException;
import org.example.interfaces.DBConnectable;
import org.example.interfaces.SQLConsumer;
import org.example.interfaces.SQLFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Класс для соединения с базой данных.
 */
public class DBConnector implements DBConnectable {

    /**
     * Имя базы данных.
     */
    private final String dbName =  System.getenv("SV_DB"); // studs

    /**
     * Имя пользователя базы данных.
     */
    private final String user =  System.getenv("SV_LOGIN"); // sXXXXXX

    /**
     * Пароль пользователя базы данных.
     */
    private final String pass =  System.getenv("SV_PASS"); // пароль из ./pgpass

    /**
     * URL-адрес для подключения к базе данных.
     */
    private final String dbUrl = "jdbc:postgresql://127.0.0.1:5432/" + dbName;

    /**
     * Конструктор класса DBConnector.
     */
    public DBConnector() {
        try {
            Class.forName("org.postgresql.Driver");
            initializeDB();
        } catch (ClassNotFoundException e) {
            ServerConfig.logger.error("Нет драйвера БД.");
            System.exit(1);
        } catch (SQLException e) {
            ServerConfig.logger.warn("Произошла ошибка при инициализации таблиц.");
            System.exit(1);
        }
    }

    /**
     * Обрабатывает запросы без возвращаемого значения.
     * @param queryBody тело запроса.
     * @throws DatabaseException в случае ошибки при работе с базой данных.
     */
    public void handleQuery(SQLConsumer<Connection> queryBody) throws DatabaseException {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pass)) {
            queryBody.accept(connection);
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при работе с БД: " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Обрабатывает запросы с возвращаемым значением.
     * @param queryBody тело запроса.
     * @param <T> тип возвращаемого значения.
     * @return результат выполнения запроса.
     * @throws DatabaseException в случае ошибки при работе с базой данных.
     */
    public <T> T handleQuery(SQLFunction<Connection, T> queryBody) throws DatabaseException {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pass)) {
            return queryBody.apply(connection);
        } catch (SQLException e) {
            throw new DatabaseException("Ошибка при работе с БД: " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Создает таблицы в базе данных, если они не существуют.
     * @throws SQLException в случае ошибки при создании таблиц.
     */
    private void initializeDB() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, user, pass);

        Statement statement = connection.createStatement();

        statement.execute("CREATE SEQUENCE IF NOT EXISTS route_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1");

        statement.execute("CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1");

        statement.execute("CREATE TABLE IF NOT EXISTS users "
                + "("
                + "login varchar(255) NOT NULL UNIQUE CHECK(login<>''),"
                + "password varchar(255) NOT NULL CHECK(password<>''),"
                + "id bigint NOT NULL PRIMARY KEY DEFAULT nextval('users_id_seq')"
                + ");");

        statement.execute("CREATE TABLE IF NOT EXISTS routes (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL CHECK (name <> ''), " +
                "coordinates_x INT NOT NULL, " +
                "coordinates_y FLOAT NOT NULL CHECK (coordinates_y > -443), " +
                "creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                "from_location_x DOUBLE NOT NULL, " +
                "from_location_y FLOAT NOT NULL, " +
                "from_location_z DOUBLE NOT NULL, " +
                "from_location_name VARCHAR(255) NOT NULL, " +
                "to_location_x DOUBLE, " +
                "to_location_y FLOAT, " +
                "to_location_z DOUBLE, " +
                "to_location_name VARCHAR(255), " +
                "distance BIGINT NOT NULL CHECK (distance > 1)" +
                ");");


        connection.close();
    }
}

