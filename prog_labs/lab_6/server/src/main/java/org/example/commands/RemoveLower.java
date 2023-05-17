package org.example.commands;


import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.RouteNotFoundException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utill.CollectionManager;
import org.example.utill.Request;
import org.example.utill.Response;
import org.example.utill.TextWriter;

/**
 * Команда 'remove_lower'. Удаляет из коллекции все элементы, меньше заданного.
 */
public class RemoveLower extends AbstractCommand {
    /**
     * Менеджер коллекции.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public RemoveLower(CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньше заданного", 1);
        this.collectionManager = collectionManager;
    }

    /**
     * Удаляет из коллекции все элементы, меньше заданного.
     * @return Статус выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            long distance = request.getNumericArgument();
            collectionManager.removeLower(distance);
            return new Response("");
        } catch (WrongAmountOfElementsException exception) {
            TextWriter.printInfoMessage("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            TextWriter.getRedText("Коллекция пуста!");
        } catch (RouteNotFoundException exception) {
            TextWriter.getRedText("Пути с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return new Response("что-то пошло не так");
    }
}
