package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

/**
 * Команда для добавления нового десантника в коллекцию
 * <p>При выполнении создает нового космического десантника через фабрику,
 * добавляет его в коллекцию и выводит присвоенный ID</p>
 */
public class AddCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    /**
     * Создает команду добавления десантника
     * @param marineFactory фабрика для создания объектов SpaceMarine
     * @param collectionManager менеджер коллекции для хранения десантников
     */
    public AddCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет добавление нового десантника в коллекцию
     * @param arg аргумент команды (не используется)
     */
    @Override
    public void execute(String arg) {
        SpaceMarine marine = marineFactory.createMarine("creation");
        collectionManager.add(marine);
        System.out.println("Новый десантник добавлен! Его ID: " + marine.getId());
    }
}