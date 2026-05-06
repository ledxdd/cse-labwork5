package server.commands;

import common.models.Chapter;
import common.services.CollectionManager;
import common.services.command_fabric.Command;

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
