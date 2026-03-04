package cse_labwork5.src.command_fabric;

import java.util.HashMap;
import java.util.Map;

public class CommandReg {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String name, Command command) {
        commands.put(name, command);
    }

    public Command get(String name) {
        return commands.get(name);
    }

    public boolean hasCommand(String name) {
        return commands.containsKey(name);
    }

    public Map<String, Command> getAll() {
        return commands;
    }
}