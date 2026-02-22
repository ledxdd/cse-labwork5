package cse_labwork5.src.commands;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.utils.Printer;

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