package commands;

import collections.CollectionManager;

public class Show implements Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор класса.
     */
    public Show(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    /**
     * Метод, исполняющий команду. Выводит описание коллекции Vector экземпляров Route.
     */
    @Override
    public void execute() {
        collectionManager.show();
    }

    /**
     * Метод, возвращающий описание команды.
     *
     * @return Возвращает описание команды info.
     */
    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }
}
