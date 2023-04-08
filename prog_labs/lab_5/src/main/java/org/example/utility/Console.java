package org.example.utility;

import org.example.exceptions.ScriptRecursionException;
import org.example.run.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Управляет вводом команд.
 */
public class Console {
    /**
     * Менеджер коллекции.
     */
    private CommandManager commandManager;
    /**
     * Сканер для чтения пользовательского ввода.
     */
    private Scanner userScanner;
    /**
     * Объект, задающий вопросы пользователю для ввода информации о пути.
     */
    private RouteAsker routeAsker;
    /**
     * Список строк, содержащих команды, которые были запущены из текущего скрипта.
     */
    private List<String> scriptStack = new ArrayList<>();

    /**
     * Конструктор для создания объекта для работы с консолью.
     * @param commandManager объект для выполнения команд.
     * @param userScanner сканер для ввода данных пользователя.
     * @param routeAsker объект для получения ввода данных пути.
     */
    public Console(CommandManager commandManager, Scanner userScanner, RouteAsker routeAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.routeAsker = routeAsker;
    }

    /**
     * Мод для запуска команд из пользовательского ввода.
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                Console.print(App.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printerror("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
        }
    }

    /**
     * Мод для запуска команд из файла.
     * @param argument аргумент.
     * @return выход из данного мода.
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = routeAsker.getUserScanner();
            routeAsker.setUserScanner(scriptScanner);
            routeAsker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(App.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            routeAsker.setUserScanner(tmpScanner);
            routeAsker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printerror("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            Console.printerror("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    /**
     * Запуск команд.
     * @param userCommand команда для запуска.
     * @return Выход из мода.
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "update_id":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(userCommand[1])) return 1;
                break;
            case "shuffle":
                if (!commandManager.shuffle(userCommand[1])) return 1;
                break;
            case "filter_by_distance":
                if (!commandManager.filterByDistance(userCommand[1])) return 1;
                break;
            case "remove_any_by_distance":
                if (!commandManager.removeAnyByDistance(userCommand[1])) return 1;
                break;
            case "group_counting_by_distance":
                if (!commandManager.groupCountingByDistance(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }

    /**
     * Выводит toOut.toString() в консоль
     * @param toOut объект для вывода
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Выводит toOut.toString() + \n в консоль
     * @param toOut объект для вывода
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Вывод ошибок: Выводит toOut.toString() в консоль
     * @param toOut ошибка для вывода
     */
    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }

    /**
     * Вывод отформатированную двух-элементную таблицу в консоль.
     * @param element1 элемент слева.
     * @param element2 элемент справа.
     */
    public static void printtable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }

    @Override
    public String toString() {
        return "Console (класс для обработки ввода команд)";
    }
}