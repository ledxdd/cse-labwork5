package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;

public class AverageHealthCommand implements Command {
    private final CollectionManager collectionManager;

    public AverageHealthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        double average = collectionManager.getAverageHealth();
        System.out.println("Усредненное здоровье: " + average);
    }
}
