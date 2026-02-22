package cse_labwork5.src.commands;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

public class AddCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;

    public AddCommand(MarineFactory marineFactory, CollectionManager collectionManager) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        SpaceMarine marine = marineFactory.createMarine("creation");
        collectionManager.add(marine);
        System.out.println("New marine added! His id is: " + marine.getId());
    }
}