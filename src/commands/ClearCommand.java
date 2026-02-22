package cse_labwork5.src.commands;

import cse_labwork5.src.services.CollectionManager;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (collectionManager.isEmpty()) {
            System.out.println("Nothing to clear. Collection is already empty!");
        } else {
            collectionManager.clear();
            System.out.println("Collection cleared!");
        }
    }
}