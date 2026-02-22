package cse_labwork5.src.commands;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

public class AddIfMinCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    public AddIfMinCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        SpaceMarine marine = marineFactory.createMarine("creation");

        if (collectionManager.isLessThanMin(marine.getHealth())) {
            collectionManager.add(marine);
            System.out.println("Marine added successfully!");
        } else {
            System.out.println("Your element is not less than least element in current collection!");
        }
    }
}