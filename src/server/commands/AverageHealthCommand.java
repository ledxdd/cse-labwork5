package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

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
            return "Среднее здоровье: " + average;
        } else {
            return "Не удалось рассчитать среднее здоровье!";
        }

    }
}
