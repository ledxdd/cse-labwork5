package server.commands;

import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.command_fabric.Command;

import java.time.ZonedDateTime;

public class AddCommand implements Command {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public Object execute(Object arg) throws Exception {
        SpaceMarine marine = (SpaceMarine) arg;
        marine.setId(SpaceMarine.generateNextId());
        marine.setCreationDate(ZonedDateTime.now());

        collectionManager.add(marine);

        return "Успешно добавлен десантник: " + marine.getId() + " с именем: " + marine.getName();
    }
}
