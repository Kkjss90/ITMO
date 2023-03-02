package commands;

public class Exit implements Command {
    /**
     * Конструктор класса.
     */
    public Exit() {
    }

    /**
     * Метод, завершающий работу программы. При завершении выводит соответствующее сообщение.
     */
    @Override
    public void execute() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

    /**
     * Метод, возвращающий описание команды.
     *
     * @return Возвращает описание команды exit.
     */
    @Override
    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }

    /** Метод, возвращающий название команды.
     *
     * @return Возвращает название команды exit.
     */
    @Override
    public String getName() {
        return "exit";
    }
}
