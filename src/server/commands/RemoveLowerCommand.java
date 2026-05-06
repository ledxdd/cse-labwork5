package server.commands;

import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.command_fabric.Command;

public class RemoveLowerCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Object execute(Object arg) throws Exception {
        if (!(arg instanceof SpaceMarine)) {
            return "Ошибка! Ожидался объект SpaceMarine";
        }

        SpaceMarine marinea = (SpaceMarine) arg;
        float targetHealth = (float) marinea.getHealth();
        int initSize = collectionManager.size();

        collectionManager.getCollection().removeIf(marine -> marine.getHealth() < targetHealth);
        int removed = initSize - collectionManager.size();
        return "Очистка завершена. Удалено десантников: " + removed;

    }
}
