package org.example.commands;

/**
 * Интерфейс Command определяет методы, которые необходимы для реализации любой команды. Интерфейс содержит три метода:
 * getDescription, getName и execute.
 */
public interface Command {
    /**
     * Метод execute выполняет действия, связанные с данной командой.
     * @param argument аргумент, который необходим для выполнения команды.
     */
    boolean execute(String argument);
    /**
     * Получает описание команды.
     * @return Описание команды в виде строки.
     */
    String getDescription();
    /**
     * Получает имя команды.
     * @return Имя команды в виде строки.
     */
    String getName();

}
