package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;

/**
 * Команда для очистки коллекции
 * <p>Удаляет все элементы из коллекции. Перед очисткой проверяет,
 * содержит ли коллекция какие-либо элементы</p>
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Создает команду очистки коллекции
     * @param collectionManager менеджер коллекции для выполнения очистки
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет очистку коллекции
     * @param arg аргумент команды (не используется)
     */
    @Override
    public void execute(String arg) {
        if (collectionManager.isEmpty()) {
            System.out.println("Нечего очищать!");
        } else {
            collectionManager.clear();
            System.out.println("Коллекция очищена!");
        }
    }
}