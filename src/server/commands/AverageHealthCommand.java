package server.commands;

import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.command_fabric.Command;

import java.util.OptionalDouble;

public class AverageHealthCommand implements Command {
    private final CollectionManager collectionManager;

    public AverageHealthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        if (collectionManager.isEmpty()) {
            return "Коллекция пуста баля!";
        }

        OptionalDouble average = collectionManager.getCollection().stream().mapToDouble(SpaceMarine::getHealth).average();

        if (average.isPresent()) {
            return "Среднее здоровье: " + average.getAsDouble();
        } else {
            return "Не удалось рассчитать среднее здоровье!";
        }

    }
}
