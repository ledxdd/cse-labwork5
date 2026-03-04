package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.utils.Printer;

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