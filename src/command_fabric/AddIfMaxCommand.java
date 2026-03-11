package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

public class AddIfMaxCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    public AddIfMaxCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

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
