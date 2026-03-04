package cse_labwork5.src.command_fabric;

import cse_labwork5.src.utils.Printer;

public class HelpCommand implements Command {
    private final Printer printer;

    public HelpCommand(CommandReg registry, Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(String arg) {
        printer.printHelp();
    }
}