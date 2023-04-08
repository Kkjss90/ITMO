package org.example.commands;

import org.example.exceptions.WrongAmountOfElementsException;
import org.example.utility.Console;

import java.util.HashMap;
import java.util.Map;

/*
 * Команда 'help'. Вывести справку по доступным командам.
 */
public class Help extends AbstractCommand {
    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     */
    public Help() {
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Вывести справку по доступным командам.
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