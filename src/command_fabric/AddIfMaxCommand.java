package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

/**
 * Команда для добавления десантника в коллекцию, если его здоровье превышает максимальное
 * <p>Создает нового десантника и сравнивает его здоровье с максимальным
 * значением в коллекции. Добавление происходит только если новый десантник
 * имеет большее значение здоровья</p>
 */
public class AddIfMaxCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    /**
     * Создает команду добавления десантника с проверкой максимального значения
     * @param marineFactory фабрика для создания объектов SpaceMarine
     * @param collectionManager менеджер коллекции для хранения и сравнения десантников
     */
    public AddIfMaxCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет добавление десантника при условии превышения максимального здоровья
     * @param arg аргумент команды (не используется)
     */
    @Override
    public void execute(String arg) {
        SpaceMarine marine = marineFactory.createMarine("creation");

        if (collectionManager.isGreaterThanMax(marine.getHealth())) {
            collectionManager.add(marine);
            System.out.println("Десантник добавлен успешно!");
        } else {
            System.out.println("Ваш элемент не больше остальных элементов в коллекции!");
        }
    }
}