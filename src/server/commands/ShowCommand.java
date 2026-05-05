package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ShowCommand implements Command {
    CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public Object execute(Object arg) throws Exception {
        if (collectionManager.isEmpty()) {
            return "коллекция пуста";
        }

        return collectionManager.getCollection().stream().sorted(Comparator.comparing(SpaceMarine::getName)).map(SpaceMarine::toString).collect(Collectors.joining("\n------------------\n"));
    }
}
