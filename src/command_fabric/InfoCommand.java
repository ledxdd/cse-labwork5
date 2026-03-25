package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.utils.Printer;

/**
 * Команда для вывода информации о коллекции.
 *
 * <p>Отображает сведения о типе коллекции, дате инициализации,
 * количестве элементов и другой служебной информации. </p>
 */

public class InfoCommand implements Command {
    private final Printer printer;

    public InfoCommand(CollectionManager collectionManager, Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(String arg) {
        printer.printInfo();
    }
}