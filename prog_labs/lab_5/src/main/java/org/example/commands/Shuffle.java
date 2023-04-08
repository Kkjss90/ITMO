package org.example.commands;

import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.CollectionManager;
import org.example.utility.Console;

/**
 * Команда 'shuffle'. Перемешивает элементы коллекции в случайном порядке.
 */
public class Shuffle extends AbstractCommand {
    /**
     Менеджер коллекции.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса ShuffleCommand.
     * @param collectionManager менеджер коллекции.
     */
    public Shuffle(CollectionManager collectionManager) {
        super("shuffle", "перемешать элементы коллекции в случайном порядке");
        this.collectionManager = collectionManager;
    }

    /**
     * Перемешивает коллекцию.
     * @return true, если выполнение команды прошло успешно, и false, если произошла ошибка.
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.shuffle();
            Console.println("Коллекция была перемешана");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printerror("Использование аргумента '" + argument + "' в команде '" + getName() + "'");
        }
        return false;
    }
}
