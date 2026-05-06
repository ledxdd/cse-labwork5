package server.commands;

import common.services.CollectionManager;
import common.services.command_fabric.Command;

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
