package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;

public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        if (collectionManager.isEmpty()) {
            System.out.println("Нечего очищать!");
        } else {
            collectionManager.clear();
            System.out.println("Коллекция очищена!");
        }
    }
}