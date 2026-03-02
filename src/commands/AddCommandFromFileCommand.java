package cse_labwork5.src.commands;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

public class AddCommandFromFileCommand implements Command {
    private final MarineFactory marineFactory;
    private final CollectionManager collectionManager;
    private final String path;

    public AddCommandFromFileCommand(MarineFactory marineFactory, CollectionManager collectionManager, String path) {
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
        this.path = path;
    }

    @Override
    public void execute(String arg) {
//        SpaceMarine marine = marineFactory.createMarineFromFile(path);
//        collectionManager.add(marine);
//        System.out.println("New marine added! His id is: " + marine.getId());
    }
}