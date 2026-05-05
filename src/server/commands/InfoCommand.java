package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class InfoCommand implements Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        StringBuilder info = new StringBuilder();
        info.append("Информация о коллекции:\n");
        info.append("    Тип: SpaceMarine\n");
        info.append("    Дата инициализации: ").append(collectionManager.getInitDate()).append("\n");
        info.append("    Количество элементов: ").append(collectionManager.size()).append("\n");

        return info.toString();
    }
}
