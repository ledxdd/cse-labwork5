package cse_labwork5.src.server.commands;

import cse_labwork5.src.common.models.Chapter;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.command_fabric.Command;

public class CountLessThanChapterCommand implements Command {
    CollectionManager collectionManager;

    public CountLessThanChapterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        Chapter targetChapter = (Chapter) arg;

        long count = collectionManager.getCollection().stream().filter(marine -> marine.getChapter() != null).filter(marine -> marine.getChapter().compareTo(targetChapter) < 0).count();

        return "Количество десантников в главах меньше заданной: " + count;
    }
}
