package org.example.file;

import org.example.collections.*;
import org.example.io.UserIO;
import org.example.exceptions.ValidValuesRangeException;

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
    public Route read(Long id) {
        String i = Instant.now().toString();
        return new Route(id, readName(), readCoordinates(), LocalDate.parse(i), readFrom(), readTo(), readDistance());
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
     * Метод, производящий чтение поля distance типа float объекта Route из потока, указанного в поле userIO.
     *
     * @return значение поля distance, уже проверенное на недопустимую ОДЗ.
     */
    public float readDistance() {
        float distance;
        while (true) {
            try{
            userIO.printCommandText("distance (not null & > 1): ");
            distance = Float.parseFloat(userIO.readLine().trim());
            if (distance <= 1) throw new ValidValuesRangeException();
            else return distance;
        } catch (ValidValuesRangeException ex) {
            System.out.println("distance должна быть больше 1");
        } catch (NumberFormatException ex) {
            System.err.println("Число должно быть типа float и не null");
        }
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
                if (x <= -443) throw new ValidValuesRangeException();
                else return x;
            } catch (ValidValuesRangeException ex) {
                System.out.println("Координата x должна быть больше -443");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Float и не null");
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
                y = Integer.parseInt(userIO.readLine().trim());
                return y;
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }

    /**
     * Метод, производящий чтение поля х типа double объекта Route из потока, указанного в поле userIO.
     *
     * @return значение поля x, уже проверенное на недопустимую ОДЗ.
     */
    public double readCoordinate_X() {
        double x;
        while (true) {
            try {
                userIO.printCommandText("coordinate_x (double & can be null): ");
                x = Double.parseDouble(userIO.readLine().trim());
                if (x==0) throw new ValidValuesRangeException();
                else return x;
            } catch (ValidValuesRangeException ex) {
                System.err.println("Число должно быть типа Double и не null");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа double");
            }
        }
    }

    /**
     * Метод, производящий чтение поля y типа Float объекта Route из потока, указанного в поле userIO.
     *
     * @return значение поля y, уже проверенное на недопустимую ОДЗ.
     */
    public Float readCoordinate_Y(){
        Float y;
        while (true){
            try{
                userIO.printCommandText("coordinate_y (float & not null): ");
                y = Float.parseFloat(userIO.readLine().trim());
                return y;
            }catch (NumberFormatException ex){
                System.err.println("Число должно быть типа float");
            }
        }
    }

    /**
     * Метод, производящий чтение поля z типа Float объекта Route из потока, указанного в поле userIO.
     *
     * @return значение поля z, уже проверенное на недопустимую ОДЗ.
     */
    public Float readCoordinate_Z(){
        Float z;
        while (true){
            try{
                userIO.printCommandText("coordinate_z (float & not null): ");
                z = Float.parseFloat(userIO.readLine().trim());
                if (z == 0) throw new ValidValuesRangeException();
                else return z;
            }catch (NumberFormatException ex){
                System.err.println("Число должно быть типа float и не null");
            }catch (ValidValuesRangeException ex){
                System.err.println("Число должно быть не null");
            }
        }
    }

    /**
     * Метод, производящий чтение координат x, y, z и переменной name.
     *
     * @return возвращает объект типа Location.
     */
    public Location readFrom(){
        return new Location(readCoordinate_X(), readCoordinateY(), readCoordinate_Z(), readName());
    }

    /**
     * Метод, производящий чтение поля z типа double объекта Route из потока, указанного в поле userIO.
     *
     * @return значение поля z, уже проверенное на недопустимую ОДЗ.
     */
    public double readCoordinateZ() {
        double z;
        while (true) {
            try{
                userIO.printCommandText("coordinate_z (double & not null): ");
                z = Double.parseDouble(userIO.readLine().trim());
                if (z==0) throw new ValidValuesRangeException();
                else return z;
            }catch (NumberFormatException ex){
                System.err.println("Число должно быть типа float и не null");
            }catch (ValidValuesRangeException ex) {
                System.err.println("Число должно быть не null");
            }
        }
    }

    /**
     * Метод, производящий чтение координат x, y, z.
     *
     * @return возвращает объект типа Position.
     */
    public Position readTo(){
        return new Position(readCoordinateX(), readCoordinate_Y(), readCoordinateZ());
    }
}