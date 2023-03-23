import collection.*;
import io.UserIO;

import java.time.Instant;
import java.time.LocalDate;

public class RouteFieldsReader {
    /**
     * Поле, которое хранит ссылку на объект типа UserIO
     */
    private UserIO userIO;

    /**
     * Конструктор класса, который присваивает в поле userIO значение, переданное в конструкторе в качестве параметра
     *
     * @param userIO хранит ссылку на объект типа UserIO
     */
    public RouteFieldsReader(UserIO userIO) {
        this.userIO = userIO;
    }
    /**
     * Метод, выводящий производящий чтение данных из консоли. Запрашивает ввод полей в строго определенном порядке.
     *
     * @param id уникальный идентификатор объекта класса Dragon, который должен быть записан в качестве ключа в коллекцию
     * @return возращает объект типа Route
     */
    public Route read(Integer id) {

        String i = Instant.now().toString();
        return new Route(id, readName(), readCoordinates(), readFrom(), readTo(), LocalDate.parse(i));
    }
}