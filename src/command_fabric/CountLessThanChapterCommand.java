package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import java.util.Scanner;

/**
 * Команда для подсчета количества десантников, чья глава содержит меньше указанного числа бойцов
 * <p>Подсчитывает элементы коллекции, у которых значение поля chapter.marinesCount
 * меньше заданного значения. Значение может быть передано как аргумент команды
 * или введено интерактивно</p>
 */
public class CountLessThanChapterCommand implements Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Создает команду подсчета десантников с меньшим количеством бойцов в главе
     * @param collectionManager менеджер коллекции для подсчета элементов
     */
    public CountLessThanChapterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет подсчет десантников, удовлетворяющих условию
     * @param arg аргумент команды, может содержать пороговое значение количества бойцов
     */
    @Override
    public void execute(String arg) {
        int count;

        if (arg != null && !arg.trim().isEmpty()) {
            try {
                count = Integer.parseInt(arg.trim());
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Ожидается число.");
                return;
            }
        } else {
            System.out.println("Введите количество десантников в главе: ");
            try {
                count = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
                return;
            }
        }

        long result = collectionManager.countLessThanChapter(count);
        System.out.println("Счетчик: " + result);
    }

}