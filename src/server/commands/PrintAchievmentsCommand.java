package server.commands;

import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.command_fabric.Command;

import java.util.Objects;
import java.util.stream.Collectors;

public class PrintAchievmentsCommand implements Command {
    private final CollectionManager collectionManager;

    public PrintAchievmentsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        if (collectionManager.isEmpty()) {
            return "Коллекция пуста!";
        }

        String result = collectionManager.getCollection().stream().map(SpaceMarine::getAchievements).filter(Objects::nonNull).sorted().collect(Collectors.joining("\n"));

        return result.isEmpty() ? "Ни у одного десантника нет достижений" : result;
    }
}
