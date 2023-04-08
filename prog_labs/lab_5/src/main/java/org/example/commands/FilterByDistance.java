package org.example.commands;

import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.CollectionManager;
import org.example.utility.Console;

/**
 * Команда 'Filter By Distance'. Выводит элементы коллекции, значение поля distance которых равно заданному.
 */
public class FilterByDistance extends AbstractCommand {
    /**
     * Менеджер коллекции
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public FilterByDistance(CollectionManager collectionManager) {
        super("filter_by_distance <DISTANCE>", "вывести элементы, значение поля distance которых равно заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит элементы коллекции, значение поля distance которых равно заданному.
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            long distance = Long.valueOf(argument.toUpperCase());
            String filteredInfo = collectionManager.distanceFilteredInfo(distance);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else Console.println("В коллекции нет пути с выбранной дистанцией!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }
}

