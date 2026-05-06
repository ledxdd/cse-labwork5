package server.commands;

import common.Request;
import common.Response;
import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.command_fabric.CommandReg;
import server.services.CollectionFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandExecutor implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);
    private final CommandReg registry;
    private final CollectionManager collectionManager;
    private final SaveCommand saveCommand;

    public CommandExecutor(CollectionManager collectionManager, CollectionFileService fileService) {
        this.collectionManager = collectionManager;
        this.saveCommand = new SaveCommand(collectionManager, fileService);
        this.registry = new CommandReg();
        registerServerCommands();
    }

    private void registerServerCommands() {
        registry.register("add", new AddCommand(collectionManager));
        registry.register("show", new ShowCommand(collectionManager));
        registry.register("update", new UpdateCommand(collectionManager));
        registry.register("remove_by_id", new RemoveByIDCommand(collectionManager));
        registry.register("clear", new ClearCommand(collectionManager));
        registry.register("remove_lower", new RemoveLowerCommand(collectionManager));
        registry.register("average_of_health", new AverageHealthCommand(collectionManager));
        registry.register("print_field_ascending_achievements", new PrintAchievmentsCommand(collectionManager));
        registry.register("add_if_max", new AddIfMaxCommand(collectionManager));
        registry.register("add_if_min", new AddIfMinCommand(collectionManager));
        registry.register("count_less_than_chapter", new CountLessThanChapterCommand(collectionManager));
        registry.register("info", new InfoCommand(collectionManager));
    }

    @Override
    public Response handleRequest(Request request) {
        String name = request.getCommandName();
        Object arg = request.getArgument();

        if ("save".equals(name)) {
            logger.warn("Попытка выполнить клиентскую команду save отклонена");
            return new Response("Команда save доступна только на сервере.");
        }
        if ("__server_save__".equals(name)) {
            try {
                logger.info("Выполняется серверная команда save");
                return new Response((String) saveCommand.execute(null));
            } catch (Exception e) {
                logger.error("Ошибка во время серверного сохранения коллекции", e);
                return new Response("Ошибка сохранения: " + e.getMessage());
            }
        }

        if (registry.hasCommand(name)) {
            try {
                logger.info("Начато выполнение команды '{}'", name);
                Object result = registry.get(name).execute(arg);
                if (result instanceof Response) {
                    return (Response) result;
                }
                if (result instanceof java.util.Collection<?> rawCollection) {
                    java.util.List<SpaceMarine> marines = rawCollection.stream()
                            .filter(SpaceMarine.class::isInstance)
                            .map(SpaceMarine.class::cast)
                            .toList();
                    if (marines.size() == rawCollection.size()) {
                        return new Response("Коллекция получена.", marines);
                    }
                }

                return new Response((String) result);

            } catch (Exception e) {
                logger.error("Ошибка при выполнении команды '{}'", name, e);
                return new Response("Ошибка при выполнении команды '" + name + "': " + e.getMessage());
            }
        }

        logger.warn("Получена неизвестная команда '{}'", name);
        return new Response("Команда '" + name + "' не найдена на сервере.");
    }
}
