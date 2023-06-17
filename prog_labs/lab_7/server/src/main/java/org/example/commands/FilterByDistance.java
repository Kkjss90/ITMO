package org.example.commands;

import org.example.dataBase.DBManager;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

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
    public FilterByDistance(CollectionManager collectionManager, DBManager dbManager) {
        super("filter_by_distance <DISTANCE>", "вывести элементы, значение поля distance которых равно заданному", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит элементы коллекции, значение поля distance которых равно заданному.
     * @return Статус выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            long distance = request.getNumericArgument();
            String filteredInfo = collectionManager.distanceFilteredInfo(distance);
            if (!filteredInfo.isEmpty()) {
                TextWriter.getWhiteText(filteredInfo);
                return new Response("");
            } else TextWriter.getRedText("В коллекции нет пути с выбранной дистанцией!");
        } catch (WrongAmountOfElementsException exception) {
            TextWriter.printInfoMessage("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            TextWriter.getRedText("Коллекция пуста!");
        }
        return new Response("что-то пошло не так");
    }
}

