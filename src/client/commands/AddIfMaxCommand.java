package client.commands;

import common.models.SpaceMarine;
import common.services.MarineFactory;
import common.services.command_fabric.Command;

public class AddIfMaxCommand implements Command {
    private final MarineFactory marineFactory;

    public AddIfMaxCommand(MarineFactory marineFactory) {
        this.marineFactory = marineFactory;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        SpaceMarine marine = marineFactory.createMarine("creation");

        return marine;
    }
}