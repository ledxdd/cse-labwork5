package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        if (!(arg instanceof SpaceMarine)) {
            throw new IllegalArgumentException("Неверный тип аргумента для UpdateCommand. Ожидается SpaceMarine.");
        }
        SpaceMarine newMarine = (SpaceMarine) arg;
        long id = newMarine.getId();

        boolean found = collectionManager.getCollection().stream().anyMatch(m -> m.getId().equals(id));

        if (!found) {
            return "Десантник с ID {" + id + "} не найден";
        }

        collectionManager.getCollection().removeIf(m -> m.getId().equals(id));
        collectionManager.add(newMarine);

        return "Десантник с id " + id + " обновлен успешно!";
    }
}
