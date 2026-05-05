package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.Command;

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
