package client.commands;

import common.services.MarineFactory;
import common.services.command_fabric.Command;

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
