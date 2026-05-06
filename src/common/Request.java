package common;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String commandName;
    private final Serializable argument;

    public Request(String commandName, Serializable argument) {
        this.argument = argument;
        this.commandName = commandName;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public Serializable getArgument() {
        return this.argument;
    }
}
