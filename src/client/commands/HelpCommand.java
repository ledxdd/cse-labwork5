package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.services.command_fabric.Command;
import cse_labwork5.src.common.services.command_fabric.CommandReg;

import java.util.Map;

public class HelpCommand implements Command {
    private final CommandReg registry;

    public HelpCommand(CommandReg reg) {
        this.registry = reg;
    }
    @Override
    public Object execute(Object arg) throws Exception {
        System.out.println("--- Список доступных команд ---");

        for (Map.Entry<String, Command> entry : registry.getAll().entrySet()) {
            String name = entry.getKey();
            Command cmd = entry.getValue();

            System.out.println(name + " : " + cmd); // TODO
        }

        return null;
    }
}
