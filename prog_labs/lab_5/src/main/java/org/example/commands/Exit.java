package org.example.commands;

import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.Console;

/**
 * Команда 'exit'. Завершение программы.
 */
public class Exit extends AbstractCommand {
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     */
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Завершение программы
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
