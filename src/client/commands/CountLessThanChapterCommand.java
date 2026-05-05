package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.models.Chapter;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.Command;

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
