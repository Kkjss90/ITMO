package org.example.utility;

import org.example.data.Coordinates;
import org.example.data.Location;
import org.example.data.Position;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.MustBeNotEmptyException;
import org.example.exceptions.NotInDeclaredLimitsException;
import org.example.run.App;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, запрашивающий у пользователя ввод
 */
public class RouteAsker {
    private final float MIN_Y_COORD = -443;
    private final long MIN_DIST = 1;

    /**
     Сканер для чтения пользовательского ввода.
     */
    private Scanner userScanner;

    /**
     * Это приватное логическое поле fileMode, которое указывает, работает ли приложение в режиме скрипта или нет.
     * Если значение этого поля установлено в true, то это означает, что приложение выполняет команды, содержащиеся в
     * скрипте, иначе оно ожидает пользовательского ввода в консоли.
     */
    private boolean fileMode;

    /**
     * Конструктор класса OrganizationAsker
     * @param userScanner сканер, использующийся для считывания пользовательского ввода
     */
    public RouteAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }
    /**
     * Настраивает сканер для сканирования пользовательского ввода.
     * @param userScanner Сканер для настройки.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return сканер, использующий пользовательский ввод.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Устанавливает routeAsker в режим 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Устанавливает routeAsker в режим 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
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
                Console.println("Введите имя:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
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
                Console.println("Введите координату X:");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Integer.parseInt(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
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
                Console.println("Введите координату Y:");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                if (y < MIN_Y_COORD) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
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
                Console.println("Введите координату X:");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Double.parseDouble(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
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
                Console.println("Введите координату Z:");
                Console.print(App.PS2);
                strZ = userScanner.nextLine().trim();
                if (fileMode) Console.println(strZ);
                z = Integer.parseInt(strZ);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Z не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Z должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
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
                Console.println("Введите координату Y:");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
    public double askZ(){
        String strZ;
        Double z;
        while (true) {
            try {
                Console.println("Введите координату Z:");
                Console.print(App.PS2);
                strZ = userScanner.nextLine().trim();
                if (fileMode) Console.println(strZ);
                z = Double.parseDouble(strZ);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Z не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Z должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
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
                Console.println("Введите дистанцию:");
                Console.print(App.PS2);
                strDistance = userScanner.nextLine().trim();
                if (fileMode) Console.println(strDistance);
                distance = Long.parseLong(strDistance);
                if (distance < MIN_DIST) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Дистанция не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Дистанция должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return distance;
    }
    /**
     * Задает пользователю вопрос.
     * @return Ответ (true/false).
     * @param question вопрос.
     * @throws IncorrectInputInScriptException если что-то пошло не так.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString() {
        return "RouteAsker (вспомогательный класс для запросов пользователю)";
    }
}
