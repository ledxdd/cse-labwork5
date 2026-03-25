package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

/**
 * Команда для удаления всех десантников с здоровьем ниже заданного значения.
 *
 * <p>Создает эталонного десантника, чье здоровье используется как пороговое значение,
 * и удаляет из коллекции всех десантников с меньшим значением здоровья. </p>
 */

public class RemoveLowerCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        SpaceMarine flagMarine = marineFactory.createMarine("creation");
        collectionManager.removeLower(flagMarine.getHealth());
        System.out.println("Удалены все десантники с здоровьем ниже: " + flagMarine.getHealth());
    }
}