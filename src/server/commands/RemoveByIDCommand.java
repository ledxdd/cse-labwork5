package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class RemoveByIDCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveByIDCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        Long id = Long.parseLong((String)arg);

        if (collectionManager.removeById(id)) {
            return "Десантник с ID {" + id + "} удален!";
        } else {
            return "Десантник не был удален (Неизвестная ошибка)";
        }
    }
}
