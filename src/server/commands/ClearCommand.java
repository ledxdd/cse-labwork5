package server.commands;

import common.services.CollectionManager;
import common.services.command_fabric.Command;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand (CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        if (collectionManager.isEmpty()) {
            return "Коллекция пуста";
        }
        collectionManager.clear();
        return "Коллекция очищена!";
    }
}
