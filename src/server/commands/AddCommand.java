package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public Object execute(Object arg) throws Exception {
        SpaceMarine marine = (SpaceMarine) arg;

        collectionManager.add(marine);

        return "Успешно добавлен десантник: " + marine.getId() + " с именем: " + marine.getName();
    }
}
