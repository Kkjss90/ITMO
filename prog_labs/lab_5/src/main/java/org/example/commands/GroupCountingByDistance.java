package org.example.commands;


import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.CollectionManager;
import org.example.utility.Console;

import java.util.Map;


/**
 * Команда 'Group Counting By Distance'. Сгруппировать элементы коллекции по значению поля distance, вывести количество элементов в каждой группе.
 */
public class GroupCountingByDistance extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public GroupCountingByDistance(CollectionManager collectionManager) {
        super("group_counting_by_distance", "сгруппировать элементы коллекции по значению поля distance, вывести количество элементов в каждой группе");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            for (Map.Entry entry : collectionManager.countByDistance().entrySet()) {
                Console.println("Дистанция: "+entry.getKey()+"\n"+"Количество элементов коллекции: " +entry.getValue());
            }
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }
}

