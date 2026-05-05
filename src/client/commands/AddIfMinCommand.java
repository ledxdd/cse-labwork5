package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.Command;

public class AddIfMinCommand implements Command {
    private final MarineFactory marineFactory;

    public AddIfMinCommand(MarineFactory marineFactory) {
        this.marineFactory = marineFactory;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        SpaceMarine marine = marineFactory.createMarine("creation");

        return marine;
    }
}