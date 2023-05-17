package org.example.commands;


import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

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
        super("group_counting_by_distance", "сгруппировать элементы коллекции по значению поля distance, вывести количество элементов в каждой группе",1);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            for (Map.Entry entry : collectionManager.countByDistance().entrySet()) {
                TextWriter.getWhiteText("Дистанция: "+entry.getKey()+"\n"+"Количество элементов коллекции: " +entry.getValue());
            }
            return new Response("");
        } catch (WrongAmountOfElementsException exception) {
            TextWriter.getRedText("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            TextWriter.getRedText("Коллекция пуста!");
        }
        return new Response("что-то пошло не так");
    }
}

