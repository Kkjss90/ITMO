package org.example.commands;

/**
 * Абстрактный класс команд.
 */
public abstract class AbstractCommand implements Command {
    /**
     * Название команды
     */
    private String name;
    /**
     * Описание команды
     */
    private String description;

    /**
     * Конструктор создает новый объект команды и задает ее имя и описание.
     * @param name название команды.
     * @param description описание команды.
     */
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return название команды.
     */
    public String getName() {
        return name;
    }

    /**
     * @return описание команда.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " (" + description + ")";
    }

    ;

    @Override
    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}

