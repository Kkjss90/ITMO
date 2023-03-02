package commands;

import collections.CollectionManager;

public class Info implements Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор класса.
     */
    public Info(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    /**
     * Метод, исполняющий команду. Выводит описание коллекции Vector экземпляров Route.
     */
    @Override
    public void execute() {
        collectionManager.info();
    }

    /**
     * Метод, возвращающий описание команды.
     *
     * @return Возвращает описание команды info.
     */
    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    /** Метод, возвращающий название команды.
     *
     * @return Возвращает название команды info.
     */
    @Override
    public String getName() {
        return "info";
    }
}
