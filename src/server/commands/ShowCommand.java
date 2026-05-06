package server.commands;

import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.command_fabric.Command;

import java.util.List;

public class ShowCommand implements Command {
    CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public Object execute(Object arg) throws Exception {
        if (collectionManager.isEmpty()) {
            return List.of();
        }

        return collectionManager.getCollection()
                .stream()
                .sorted((left, right) -> left.getName().compareTo(right.getName()))
                .toList();
    }
}
