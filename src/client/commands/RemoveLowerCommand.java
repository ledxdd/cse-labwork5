package cse_labwork5.src.client.commands;

import cse_labwork5.src.common.services.MarineFactory;
import cse_labwork5.src.common.services.command_fabric.Command;

import java.io.Serializable;

public class RemoveLowerCommand implements Command {
    private final MarineFactory marineFactory;

    public RemoveLowerCommand(MarineFactory mf) {
        this.marineFactory = mf;
    }

    @Override
    public Serializable execute(Object arg) throws Exception {
        System.out.println("ВВедите данные десантника. Все элементы, чье здоровье меньше введенного будут удалены");

        return marineFactory.createMarine("creation");
    }
}
