package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class RemoveByIDCommand implements Command {
    CollectionManager collectionManager;

    public RemoveByIDCommand (CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        try {
            if (arg == null || arg.toString().isEmpty()) {
                throw new Exception("Не указан ID для удаления.");
            }

            return Long.valueOf(arg.toString());
        } catch (NumberFormatException e) {
            throw new Exception("ID должен быть целым числом.");
        }
    }
}
