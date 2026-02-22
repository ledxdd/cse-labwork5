package cse_labwork5.src.commands;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

public class RemoveLowerCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        SpaceMarine flagMarine = marineFactory.createMarine("creation");
        collectionManager.removeLower(flagMarine.getHealth());
        System.out.println("Removed all marines with health lower than " + flagMarine.getHealth());
    }
}