package client.commands;

import common.models.SpaceMarine;
import common.services.MarineFactory;
import common.services.command_fabric.Command;

public class UpdateCommand implements Command {
    private final MarineFactory marineFactory;

    public UpdateCommand(MarineFactory marineFactory) {
        this.marineFactory = marineFactory;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        long id = Long.parseLong((String)arg);

        SpaceMarine marine = marineFactory.createMarine("update");

        marine.setId(id);

        return marine;
    }
}
