package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.Request;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.CommandReg;

import java.io.Serializable;

public class CommandExecutor {
    private final CommandReg registry;
    private final MarineFactory marineFactory;

    public CommandExecutor (MarineFactory marineFactory) {
        this.marineFactory = marineFactory;
        this.registry = new CommandReg();
        registerClientCommands();
    }

    private void registerClientCommands() {
        registry.register("add", new AddCommand(marineFactory));
        registry.register("addIfMax", new AddIfMaxCommand(marineFactory));
        registry.register("addIfMin", new AddIfMinCommand(marineFactory));
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("clearCommand", new ClearCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("help", new HelpCommand(registry));
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());
        registry.register("averageHealth", new AverageHeatlhCommand());

    }

    public Request buildRequest(String input) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String arg = parts.length > 1 ? parts[1] : null;

        if (registry.hasCommand(commandName)) {
            try {
                Object payload = registry.get(commandName).execute(arg);
                return new Request(commandName, (Serializable) payload);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
