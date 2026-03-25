package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;

/**
 * Команда для удаления десантника по идентификатору.
 *
 * <p>Удаляет элемент коллекции с указанным ID. Если элемент не найден,
 * выводит соответствующее сообщение. </p>
 */

public class RemoveByIdCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (arg == null) {
            System.out.println("Please provide an id: remove_by_id <id>");
            return;
        }

        try {
            long id = Long.parseLong(arg);
            boolean removed = collectionManager.removeById(id);

            if (removed) {
                System.out.println("Marine with id: " + id + "was removed");
            } else {
                System.out.println("Marine with id: " + id + "was not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format!");
        }
    }
}