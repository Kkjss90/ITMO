package org.example.run;

import org.example.commands.*;
import org.example.utility.*;

import java.util.Scanner;

/**
 * Главный класс. Запуск программы
 * @author Князева Александра P3120
 */
public class App {
    public static final String PS1 = ">> ";
    public static final String PS2 = "> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "LAB5";

            RouteAsker routeAsker = new RouteAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new Help(),
                    new Info(collectionManager),
                    new Show(collectionManager),
                    new Add(collectionManager, routeAsker),
                    new RemoveById(collectionManager),
                    new Save(collectionManager),
                    new Clear(collectionManager),
                    new Exit(),
                    new ExecuteScript(),
                    new RemoveGreater(collectionManager, routeAsker),
                    new RemoveLower(collectionManager, routeAsker),
                    new Shuffle(collectionManager),
                    new FilterByDistance(collectionManager),
                    new RemoveAnyByDistance(collectionManager),
                    new GroupCountingByDistance(collectionManager),
                    new UpdateId(collectionManager, routeAsker)
            );
            Console console = new Console(commandManager, userScanner, routeAsker);

            console.interactiveMode();
        }
    }
}
