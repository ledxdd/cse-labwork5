package cse_labwork5.src.command_fabric;

import cse_labwork5.src.utils.Printer;

/**
 * Команда для отображения справки по всем доступным командам.
 *
 * <p>Выводит список всех зарегистрированных команд с их описанием.
 */

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