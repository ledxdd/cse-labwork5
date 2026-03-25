package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

/**
 * Команда для добавления десантника в коллекцию, если его здоровье меньше минимального.
 *
 * <p>Создает нового десантника и сравнивает его здоровье с минимальным
 * значением в коллекции. Добавление происходит только если новый десантник
 * имеет меньшее значение здоровья.
 */

public class AddIfMinCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    public AddIfMinCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        SpaceMarine marine = marineFactory.createMarine("creation");

        if (collectionManager.isLessThanMin(marine.getHealth())) {
            collectionManager.add(marine);
            System.out.println("Десантник добавлен успешно!");
        } else {
            System.out.println("Ваш элемент не меньше остальных элементов в коллекции!");
        }
    }
}