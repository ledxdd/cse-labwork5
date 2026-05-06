package client.commands;

import common.Request;
import common.services.MarineFactory;
import common.services.command_fabric.CommandReg;

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
        registry.register("add_if_max", new AddIfMaxCommand(marineFactory));
        registry.register("add_if_min", new AddIfMinCommand(marineFactory));
        registry.register("average_of_health", new AverageHeatlhCommand());
        registry.register("clear", new ClearCommand());
        registry.register("show", new ShowCommand());
        registry.register("info", new InfoCommand());
        registry.register("update", new UpdateCommand(marineFactory));
        registry.register("remove_by_id", new RemoveByIDCommand());
        registry.register("remove_lower", new RemoveLowerCommand(marineFactory));
        registry.register("count_less_than_chapter", new CountLessThanChapterCommand(marineFactory));
        registry.register("print_field_ascending_achievements", new PrintAchievmentsCommand());
        registry.register("help", new HelpCommand(registry));
    }

    public Request buildRequest(String input) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0].trim();
        String arg = parts.length > 1 ? parts[1] : null;

        if ("help".equals(commandName)) {
            try {
                registry.get("help").execute(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        if (registry.hasCommand(commandName)) {
            try {
                Object payload = registry.get(commandName).execute(arg);
                return new Request(commandName, (Serializable) payload);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        throw new IllegalArgumentException("Команда '" + commandName + "' не найдена.");
    }
}
