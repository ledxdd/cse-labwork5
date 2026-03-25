package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;
import cse_labwork5.src.utils.Printer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Исполнитель команд, управляющий регистрацией и выполнением всех доступных команд.
 *
 * <p>Содержит реестр команд, фабрику для создания десантников и менеджер коллекции.
 * Отвечает за инициализацию команд, их регистрацию и маршрутизацию входящих запросов.
 */

public class CommandExecutor {
    private final CollectionManager collectionManager;
    private final MarineFactory marineFactory;
    private final Printer printer;
    private final CommandReg registry;
    private boolean isRunning = true;

    public CommandExecutor(CollectionManager collectionManager, Scanner scanner) throws IOException {
        this.collectionManager = collectionManager;
        this.marineFactory = new MarineFactory(scanner, collectionManager);
        this.printer = new Printer(collectionManager);
        this.registry = new CommandReg();
        registerCommands();
    }

    private void registerCommands() throws IOException {
        registry.register("help", new HelpCommand(registry, printer));
        registry.register("info", new InfoCommand(collectionManager, printer));
        registry.register("show", new ShowCommand(collectionManager, printer));
        registry.register("add", new AddCommand(marineFactory, collectionManager));
        registry.register("update", new UpdateCommand(collectionManager, marineFactory));
        registry.register("remove_by_id", new RemoveByIdCommand(collectionManager));
        registry.register("clear", new ClearCommand(collectionManager));
        registry.register("exit", new ExitCommand(() -> isRunning = false));
        registry.register("add_if_max", new AddIfMaxCommand(marineFactory, collectionManager));
        registry.register("add_if_min", new AddIfMinCommand(marineFactory, collectionManager));
        registry.register("remove_lower", new RemoveLowerCommand(marineFactory, collectionManager));
        registry.register("average_of_health", new AverageHealthCommand(collectionManager));
        registry.register("count_less_than_chapter", new CountLessThanChapterCommand(collectionManager));
        registry.register("print_field_ascending_achievements", new PrintAchievementsCommand(collectionManager));
        registry.register("execute_script", new ExecuteCommand(this, marineFactory, collectionManager));
        registry.register("save", new SaveCommand(marineFactory, collectionManager));
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void execute(String input) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String arg = parts.length > 1 ? parts[1] : null;

        if (registry.hasCommand(commandName)) {
            try {
                registry.get(commandName).execute(arg);
            } catch (Exception e) {
                System.out.println("Проблема с выполнением команды: " + e.getMessage());
            }
        } else {
            System.out.println("Неверный ввод! Введите 'help' для отображения списка команд");
        }
    }
}