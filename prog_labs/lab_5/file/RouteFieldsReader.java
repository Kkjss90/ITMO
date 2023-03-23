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
     * @param id уникальный идентификатор объекта класса Route, который должен быть записан в качестве ключа в коллекцию
     * @return возращает объект типа Route
     */
    public Route read(Integer id) {
        String i = Instant.now().toString();
        return new Route(id, readName(), readCoordinates(), readFrom(), readTo(), readDistance(), LocalDate.parse(i));
    }
    /**
     * Метод, производящий чтение поля name типа String объекта Route из потока, указанного в поле userIO. При некорректном вводе просит ввести поля заново.
     *
     * @return значение поля name, уже проверенное на недопустимую ОДЗ.
     */
    public String readName() {
        String str;

        while (true) {
            userIO.printCommandText("name (not null): ");
            str = userIO.readLine().trim();
            if (str.equals("")) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой \n");
            else return str;
        }
    }
    /**
     * Метод, производящий чтение координат x, y.
     *
     * @return возвращает объект типа Coordinates.
     */
    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    /**
     * Метод, производящий чтение поля x типа Float объекта Route из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля x, уже проверенное на недопустимую ОДЗ.
     */
    public Float readCoordinateX() {
        Float x;
        while (true) {
            try {
                userIO.printCommandText("coordinate_x (float & not null & x > -443): ");
                x = Float.parseFloat(userIO.readLine().trim());
                if (x <= -648) throw new ValidValuesRangeException();
                else return x;
            } catch (ValidValuesRangeException ex) {
                System.out.println("Координата x должна быть больше -443");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Double и не null");
            }
        }
    }

    /**
     * Метод, производящий чтение поля y типа int объекта Route из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля y, уже проверенное на недопустимую ОДЗ.
     */
    public int readCoordinateY() {
        int y;
        while (true) {
            try {
                userIO.printCommandText("coordinate_y (int & can be null): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) y = 0;
                return y;
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }
    public double readCoordinate_X(){
        double x;
        while (true){
            try{
                userIO.printCammandText("coordinate_x (double & can be null): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) x = 0;
            }catch (NemberFormatException ex){
                System.err.println("Число должно быть типа double");
            }
        }
    }
    public int readCoordinate_Y(){
        int y;
        while (true){
            try{
                userIO.printCammandText("coordinate_y (int & can be null): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) y = 0;
            }catch (NemberFormatException ex){
                System.err.println("Число должно быть типа int");
            }
        }
    }
    public Float readCoordinate_Z(){
        Float z;
        while (true){
            try{
                userIO.printCammandText("coordinate_z (float & not null): ");
                String str = userIO.readLine().trim();
                if (z = null) throw new ValidValuesRangeException();
                else return z;
            }catch (NemberFormatException ex){
                System.err.println("Число должно быть типа float и не null");
            }
        }
    }
    public Location readFrom(){
        return new Location(readName(), readCoordinate_X(), readCoordinate_Y(), readCoordinate_Z());
    }
    public Position readTo(){
        return
    }
}