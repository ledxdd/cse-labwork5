package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.Command;

public class AddIfMinCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает команду добавления десантника с проверкой максимального значения
     * @param marineFactory фабрика для создания объектов SpaceMarine
     * @param collectionManager менеджер коллекции для хранения и сравнения десантников
     */
    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет добавление десантника при условии превышения максимального здоровья
     * @param arg аргумент команды (не используется)
     */
    @Override
    public Object execute(Object arg) throws Exception {
        if (!(arg instanceof SpaceMarine)) {
            throw new IllegalArgumentException("Неверный тип аргумента для AddIfMaxCommand. Ожидается SpaceMarine.");
        }
        SpaceMarine marine = (SpaceMarine) arg;

        if (collectionManager.isLessThanMin(marine.getHealth())) {
            collectionManager.add(marine);
            return "Десантник добавлен успешно!";
        } else {
            return "Ваш элемент не меньше остальных элементов в коллекции!";
        }
    }
}