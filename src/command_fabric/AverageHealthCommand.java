package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;

/**
 * Команда для вычисления среднего значения здоровья всех десантников.
 *
 * <p>Запрашивает у менеджера коллекции среднее арифметическое значение
 * здоровья всех элементов и выводит результат в консоль. </p>
 */

public class AverageHealthCommand implements Command {
    private final CollectionManager collectionManager;

    public AverageHealthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        double average = collectionManager.getAverageHealth();
        System.out.println("Усредненное здоровье: " + average);
    }
}
