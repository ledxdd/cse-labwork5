package client.commands;

import common.services.MarineFactory;
import common.services.command_fabric.Command;

/**
 * Команда для добавления нового десантника в коллекцию
 * <p>При выполнении создает нового космического десантника через фабрику,
 * добавляет его в коллекцию и выводит присвоенный ID</p>
 */
public class AddCommand implements Command {
    private final MarineFactory marineFactory;

    /**
     * Создает команду добавления десантника
     * @param marineFactory фабрика для создания объектов SpaceMarine
     */
    public AddCommand(MarineFactory marineFactory) {
        this.marineFactory = marineFactory;
    }

    /**
     * Выполняет добавление нового десантника в коллекцию
     * @param arg аргумент команды (не используется)
     */
    @Override
    public Object execute(Object arg) {

        return marineFactory.createMarine("creation");
    }
}