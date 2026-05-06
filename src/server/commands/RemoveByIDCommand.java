package server.commands;

import common.services.CollectionManager;
import common.services.command_fabric.Command;

public class RemoveByIDCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIDCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        final Long id;
        if (arg instanceof Number) {
            id = ((Number) arg).longValue();
        } else if (arg instanceof String) {
            id = Long.parseLong((String) arg);
        } else {
            return "Некорректный ID.";
        }

        if (collectionManager.removeById(id)) {
            return "Десантник с ID {" + id + "} удален!";
        } else {
            return "Десантник с ID {" + id + "} не найден.";
        }
    }
}
