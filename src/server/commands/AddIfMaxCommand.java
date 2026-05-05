package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.Command;

/**
 * Команда для добавления десантника в коллекцию, если его здоровье превышает максимальное
 * <p>Создает нового десантника и сравнивает его здоровье с максимальным
 * значением в коллекции. Добавление происходит только если новый десантник
 * имеет большее значение здоровья</p>
 */
public class AddIfMaxCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает команду добавления десантника с проверкой максимального значения
     * @param marineFactory фабрика для создания объектов SpaceMarine
     * @param collectionManager менеджер коллекции для хранения и сравнения десантников
     */
    public AddIfMaxCommand(CollectionManager collectionManager) {
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

        if (collectionManager.isGreaterThanMax(marine.getHealth())) {
            collectionManager.add(marine);
            return "Десантник добавлен успешно!";
        } else {
            return "Ваш элемент не больше остальных элементов в коллекции!";
        }
    }
}