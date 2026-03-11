package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;

public class PrintAchievementsCommand implements Command {
    private final CollectionManager collectionManager;

    public PrintAchievementsCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        int index = 1;
        if (collectionManager.isEmpty()) {
            System.out.println("Коллекция пуста!");
        } else {
            for (SpaceMarine marine : collectionManager.getCollection()) {
                System.out.println("\n Десантник #" + index++ + " (ID: " + marine.getId() + ")");
                System.out.println("   ├─ Достижения: " +
                        (marine.getAchievements() != null ? marine.getAchievements() : "никаких..."));
            }
        }
    }
}