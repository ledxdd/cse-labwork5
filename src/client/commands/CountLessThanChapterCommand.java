package client.commands;

import common.models.Chapter;
import common.services.MarineFactory;
import common.services.command_fabric.Command;

public class CountLessThanChapterCommand implements Command {
    private final MarineFactory marineFactory;

    public CountLessThanChapterCommand(MarineFactory marineFactory) {
        this.marineFactory = marineFactory;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        Chapter chapter = marineFactory.createChapter();

        return chapter;
    }
}
