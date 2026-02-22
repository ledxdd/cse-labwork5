package cse_labwork5.src.commands;

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
        for (SpaceMarine marine : collectionManager.getCollection()) {
            System.out.println("\n🔹 MARINE #" + index++ + " (ID: " + marine.getId() + ")");
            System.out.println("   ├─ Achievements: " +
                    (marine.getAchievements() != null ? marine.getAchievements() : "null"));
        }
    }
}