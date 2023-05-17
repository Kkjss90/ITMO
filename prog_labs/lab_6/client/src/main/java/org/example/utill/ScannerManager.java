package org.example.utill;

import org.example.data.Coordinates;
import org.example.data.Location;
import org.example.data.Position;
import org.example.data.Route;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.NotInDeclaredLimitsException;
import org.example.exceptions.NotNullException;


import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, запрашивающий у пользователя ввод
 */
public class ScannerManager {
    private final float MIN_Y_COORD = -443;
    private final long MIN_DIST = 1;

    /**
     * Константа строки приглашения для ввода в интерактивном режиме.
     */
    public static final String INPUT_INFO = "> ";

    /**
     * Константа строки приглашения для ввода команд в интерактивном режиме.
     */
    public static final String INPUT_COMMAND = "$ ";

    /**
     * Сканер для ввода.
     */
    private Scanner userScanner;

    /**
     * Режим скрипта.
     */
    private boolean scriptMode;

    /**
     * Шаблон для проверки числа.
     */
    private final String numberPattern = "-?\\d+(\\.\\d+)?";

    /**
     * Создает менеджер для ввода с помощью заданного сканера.
     * @param scanner сканер для ввода.
     * @param scriptMode режим чтение со скрипта
     */
    public ScannerManager(Scanner scanner, boolean scriptMode) {
        this.userScanner = scanner;
        this.scriptMode = scriptMode;
    }

    public Route askRoute() throws IncorrectInputInScriptException {
        return new Route(
                0,
                askName(),
                askCoordinates(),
                new Date(),
                askLocation(),
                askPosition(),
                askDistance()
        );
    }

    /**
     * Спрашивает у пользователя название пути.
     * @return название пути.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите имя пути:");
                System.out.print(INPUT_INFO);
                name = userScanner.nextLine().trim();
                if (scriptMode) TextWriter.printInfoMessage(name);
                if (name.equals("")) throw new NotNullException();
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                System.exit(1);
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NotNullException exception) {
                TextWriter.printErr("Значение поля не может быть пустым.");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    TextWriter.printErr("Работа программы прекращена.");
                    System.exit(1);
                }
            } catch (IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return name;
    }
    /**
     * Спрашивает у пользователя координату Х типа int.
     * @return координата Х типа int.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public int askX() throws IncorrectInputInScriptException {
        String strX;
        int x;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату X:");
                System.out.print(INPUT_INFO);
                strX = userScanner.nextLine().trim();
                strX = strX.replace(",", ".");
                if (scriptMode) TextWriter.printInfoMessage(strX);
                if (!strX.matches(numberPattern)) throw new NumberFormatException();
                x = Integer.parseInt(strX);
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    TextWriter.printErr("Работа программы прекращена.");
                    System.exit(1);
                }
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Значение поля должно быть int.");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return x;
    }
    /**
     * Спрашивает у пользователя координату Y типа Float.
     * @return координата Y типа Float.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public Float askY() throws IncorrectInputInScriptException {
        String strY;
        Float y;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату Y:");
                System.out.print(INPUT_INFO);
                strY = userScanner.nextLine().trim();
                strY = strY.replace(",", ".");
                if (scriptMode) TextWriter.printInfoMessage(strY);
                if (!strY.matches(numberPattern)) throw new NumberFormatException();
                y = Float.parseFloat(strY);
                if (y < MIN_Y_COORD) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                System.exit(1);
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Значение поля должно быть Float.");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            } catch (NotInDeclaredLimitsException e) {
                TextWriter.printErr("Координата Y меньше минимальной (-443)");
            }
        }
        return y;
    }
    /**
     * Спрашивает у пользователя координаты.
     * @return координаты.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException{
        int x;
        Float y;
        x=askX();
        y=askY();
        return new Coordinates(x, y);
    }
    /**
     * Спрашивает у пользователя координату Х типа Double.
     * @return координата Х типа Double.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public Double ask_X() throws IncorrectInputInScriptException{
        String strX;
        Double x;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату X:");
                System.out.print(INPUT_INFO);
                strX = userScanner.nextLine().trim();
                strX = strX.replace(",", ".");
                if (scriptMode) TextWriter.printInfoMessage(strX);
                if (!strX.matches(numberPattern)) throw new NumberFormatException();
                x = Double.parseDouble(strX);
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    TextWriter.printErr("Работа программы прекращена.");
                    System.exit(1);
                }
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Значение поля должно быть int.");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return x;
    }
    /**
     * Спрашивает у пользователя координату Z типа int.
     * @return координата Z типа int.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public int ask_Z() throws IncorrectInputInScriptException{
        String strZ;
        int z;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату Z:");
                System.out.print(INPUT_INFO);
                strZ = userScanner.nextLine().trim();
                strZ = strZ.replace(",", ".");
                if (scriptMode) TextWriter.printInfoMessage(strZ);
                if (!strZ.matches(numberPattern)) throw new NumberFormatException();
                z = Integer.parseInt(strZ);
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    TextWriter.printErr("Работа программы прекращена.");
                    System.exit(1);
                }
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Значение поля должно быть int.");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return z;
    }
    /**
     * Спрашивает у пользователя координаты локации.
     * @return координаты локации.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public Location askLocation() throws IncorrectInputInScriptException{
        Double x;
        Float y;
        int z;
        String name;
        x=ask_X();
        y=askY();
        z=ask_Z();
        name=askName();
        return new Location(x,y,z, name);
    }
    /**
     * Спрашивает у пользователя координату Y типа int.
     * @return координата Y типа int.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public int ask_Y() throws IncorrectInputInScriptException{
        String strY;
        int y;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату Y:");
                System.out.print(INPUT_INFO);
                strY = userScanner.nextLine().trim();
                strY = strY.replace(",", ".");
                if (scriptMode) TextWriter.printInfoMessage(strY);
                if (!strY.matches(numberPattern)) throw new NumberFormatException();
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                System.exit(1);
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Значение поля должно быть int.");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return y;
    }
    /**
     * Спрашивает у пользователя координату Z типа double.
     * @return координата Z типа double.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public double askZ(){
        String strZ;
        double z;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату Z:");
                System.out.print(INPUT_INFO);
                strZ = userScanner.nextLine().trim();
                strZ = strZ.replace(",", ".");
                if (scriptMode) TextWriter.printInfoMessage(strZ);
                if (!strZ.matches(numberPattern)) throw new NumberFormatException();
                z = Double.parseDouble(strZ);
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Значение поля не может быть использовано.");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if (!userScanner.hasNext()) {
                    TextWriter.printErr("Работа программы прекращена.");
                    System.exit(1);
                }
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Значение поля должно быть double.");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка.");
                System.exit(1);
            }
        }
        return z;
    }
    /**
     * Спрашивает у пользователя координаты позиции.
     * @return координаты позиции.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public Position askPosition() throws IncorrectInputInScriptException{
        int x;
        int y;
        double z;
        x=askX();
        y=ask_Y();
        z=askZ();
        return new Position(x,y,z);
    }
    /**
     * Спрашивает у пользователя дистанцию.
     * @return дистанция.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public long askDistance(){
        String strDistance;
        long distance;
        while (true) {
            try {
                TextWriter.printInfoMessage("Введите координату Z:");
                System.out.print(INPUT_INFO);
                strDistance = userScanner.nextLine().trim();
                distance = Long.parseLong(strDistance);
                if (distance < MIN_DIST) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                TextWriter.printErr("Дистанция не распознана!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                TextWriter.printErr("Дистанция должна быть представлена числом!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                TextWriter.printErr("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                TextWriter.printErr("Дистанция меньше минимальной (1)");
            }
        }
        return distance;
    }

    @Override
    public String toString() {
        return "RouteAsker (вспомогательный класс для запросов пользователю)";
    }
}
