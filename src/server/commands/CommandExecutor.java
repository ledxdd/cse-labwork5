package cse_labwork5.src.server.commands;

import cse_labwork5.src.client.commands.AverageHeatlhCommand;
import cse_labwork5.src.common.Request;
import cse_labwork5.src.common.Response;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.CommandReg;

public class CommandExecutor {
    private final CommandReg registry;
    private final CollectionManager collectionManager;

    public CommandExecutor(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.registry = new CommandReg();
        registerServerCommands();
    }

    private void registerServerCommands() {
        // Регистрируем команды с правильными именами (теми, что пришлет клиент)
        registry.register("add", new AddCommand(collectionManager));
        registry.register("show", new ShowCommand(collectionManager));
        registry.register("update", new UpdateCommand(collectionManager));
        registry.register("remove_by_id", new RemoveByIDCommand(collectionManager));
        registry.register("clear", new ClearCommand(collectionManager));
        registry.register("remove_lower", new RemoveLowerCommand(collectionManager));
        registry.register("average_of_health", new AverageHealthCommand(collectionManager));
        registry.register("print_field_ascending_achievements", new PrintAchievmentsCommand(collectionManager));
        // Добавь остальные по аналогии...
    }

    public Response handleRequest(Request request) {
        String name = request.getCommandName();
        Object arg = request.getArgument();

        if (registry.hasCommand(name)) {
            try {

                Object result = registry.get(name).execute(arg);


                if (result instanceof Response) {
                    return (Response) result;
                }

                return new Response((String) result);

            } catch (Exception e) {
                return new Response("Ошибка при выполнении команды '" + name + "': " + e.getMessage());
            }
        }

        return new Response("Команда '" + name + "' не найдена на сервере.");
    }
}
