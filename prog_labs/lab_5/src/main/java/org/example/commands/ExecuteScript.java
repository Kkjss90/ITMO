package org.example.commands;


import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.Console;

/**
 * Команда 'execute_script'. Выполняет скрипт из указанного файла.
 */
public class ExecuteScript extends AbstractCommand {
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     */
    public ExecuteScript() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    /**
     * Выполняет скрипт из указанного файла..
     * @return Статус выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
