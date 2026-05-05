package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand (CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        if (collectionManager.isEmpty()) {
            return "Коллекция пуста";
        } else {
            return "Коллекция очищена!";
        }
    }
}
