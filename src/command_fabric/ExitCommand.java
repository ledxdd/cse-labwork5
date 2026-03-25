package cse_labwork5.src.command_fabric;

/**
 * Команда для завершения работы приложения.
 *
 * <p>Выполняет переданное действие завершения, обычно останавливающее
 * основной цикл выполнения программы.
 */

public class ExitCommand implements Command {
    private final Runnable exitAction;

    public ExitCommand(Runnable exitAction) {
        this.exitAction = exitAction;
    }

    @Override
    public void execute(String arg) {
        exitAction.run();
    }
}