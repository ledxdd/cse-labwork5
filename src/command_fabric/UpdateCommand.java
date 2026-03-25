package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

/**
 * Команда для обновления десантника по идентификатору.
 *
 * <p>Заменяет существующего десантника с указанным ID на нового,
 * сохраняя при этом исходные идентификатор и дату создания.
 */

public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;
    private final MarineFactory marineFactory;

    public UpdateCommand(CollectionManager collectionManager, MarineFactory marineFactory) {
        this.collectionManager = collectionManager;
        this.marineFactory = marineFactory;
    }

    @Override
    public void execute(String arg) {
        if (arg == null) {
            System.out.println("Пожалуйста введите id: update <id>");
            return;
        }

        try {
            long id = Long.parseLong(arg);
            SpaceMarine oldMarine = collectionManager.findById(id);

            if (oldMarine == null) {
                System.out.println("Десантник с id " + id + " не найден!");
                return;
            }

            SpaceMarine newMarine = marineFactory.createMarine("update");
            newMarine.setId(oldMarine.getId());
            newMarine.setCreationDate(oldMarine.getCreationDate());

            collectionManager.remove(oldMarine);
            collectionManager.add(newMarine);

            System.out.println("Десантник добавлен успешно!");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id!");
        }
    }
}