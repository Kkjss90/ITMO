package org.example.commands;


import org.example.exceptions.*;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreater extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный.
     * @return Статус выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            long distance = request.getNumericArgument();
            collectionManager.removeGreater(distance);
            return new Response("");
        } catch (WrongAmountOfElementsException exception) {
            TextWriter.printInfoMessage("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            TextWriter.getRedText("Коллекция пуста!");
        } catch (RouteNotFoundException exception) {
            TextWriter.getRedText("Пути с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return new Response("что-то не так");
    }
}
