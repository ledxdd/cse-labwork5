package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.utils.Printer;

/**
 * Команда для отображения всех элементов коллекции.
 *
 * <p>Выводит содержимое коллекции в удобочитаемом формате,
 * включая все поля каждого десантника. </p>
 */

public class ShowCommand implements Command {
    private final Printer printer;

    public ShowCommand(CollectionManager collectionManager, Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(String arg) {
        printer.printCollection();
    }
}