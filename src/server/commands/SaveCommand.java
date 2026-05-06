package server.commands;

import common.services.CollectionManager;
import common.services.command_fabric.Command;
import server.services.CollectionFileService;

public class SaveCommand implements Command {
    private final CollectionManager collectionManager;
    private final CollectionFileService fileService;

    public SaveCommand(CollectionManager collectionManager, CollectionFileService fileService) {
        this.collectionManager = collectionManager;
        this.fileService = fileService;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        fileService.save(collectionManager);
        return "Коллекция успешно сохранена в файл.";
    }
}
