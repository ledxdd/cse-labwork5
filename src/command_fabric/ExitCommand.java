package cse_labwork5.src.command_fabric;

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