package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.services.command_fabric.Command;

public class ExitCommand implements Command {
    @Override
    public Object execute(Object arg) throws Exception {
        System.out.println("Завершение работы клиента!");
        System.exit(0);
        return null;
    }
}
