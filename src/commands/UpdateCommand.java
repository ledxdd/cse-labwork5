package cse_labwork5.src.commands;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

public class UpdateCommand implements Command {
    private final CollectionManager collectionManager;
    private final MarineFactory marineFactory;

    public UpdateCommand(CollectionManager collectionManager, MarineFactory marineFactory) {
        this.collectionManager = collectionManager;
        this.marineFactory = marineFactory;
    }

    @Override
    public void execute(String arg) {
        if (arg == null) {
            System.out.println("Please provide an id: update <id>");
            return;
        }

        try {
            long id = Long.parseLong(arg);
            SpaceMarine oldMarine = collectionManager.findById(id);

            if (oldMarine == null) {
                System.out.println("Marine with id " + id + " not found!");
                return;
            }

            SpaceMarine newMarine = marineFactory.createMarine("update");
            newMarine.setId(oldMarine.getId());
            newMarine.setCreationDate(oldMarine.getCreationDate());

            collectionManager.remove(oldMarine);
            collectionManager.add(newMarine);

            System.out.println("Marine updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format!");
        }
    }
}