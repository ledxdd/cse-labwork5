package cse_labwork5.src.common.services.utils;

import cse_labwork5.src.common.models.SpaceMarine;
import cse_labwork5.src.common.services.CollectionManager;

import java.util.stream.Collectors;

/**
 * Класс для формирования строкового представления информации о коллекции
 */
public class Printer {
    private final CollectionManager collectionManager;

    public Printer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Возвращает справку (список всех команд)
     */
    public String getHelp() {
        return "info : вывести информацию о коллекции\n" +
                "show : вывести все элементы коллекции\n" +
                "add {element} : добавить новый элемент (Элемент будет вводиться построчно после написание команды add)\n" +
                "update {id} {element} : обновить элемент (id вводится в той же строке что и update, (Элемент будет вводиться построчно после написание команды update {id}))\n" +
                "remove_by_id id : удалить элемент по id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : исполнить скрипт\n" +
                "exit : завершить программу\n" +
                "add_if_max {element} : добавить, если больше максимального\n" +
                "add_if_min {element} : добавить, если меньше минимального\n" +
                "remove_lower {element} : удалить меньшие элементы\n" +
                "average_of_health : среднее значение health\n" +
                "count_less_than_chapter chapter : количество элементов с chapter меньше\n" +
                "print_field_ascending_achievements : значения achievements по возрастанию";
    }

    /**
     * Возвращает информацию о коллекции
     */
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(50)).append("\n");
        sb.append("Информация о коллекции:\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("Тип: TreeSet<SpaceMarine>\n");
        sb.append("Дата инициализации: ").append(collectionManager.getInitDate()).append("\n");
        sb.append("Количество элементов: ").append(collectionManager.size()).append("\n");
        sb.append("=".repeat(50)).append("\n");
        return sb.toString();
    }

    /**
     * Возвращает строковое представление всей коллекции
     */
    public String getCollection() {
        if (collectionManager.isEmpty()) {
            return "Коллекция пуста!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(100)).append("\n");
        sb.append("Total marines: ").append(collectionManager.size()).append("\n");
        sb.append("=".repeat(100)).append("\n");

        int index = 1;
        for (SpaceMarine m : collectionManager.getCollection()) {
            sb.append(getMarineString(m, index++));
        }
        sb.append("\n").append("=".repeat(100)).append("\n");
        return sb.toString();
    }

    /**
     * Возвращает строковое представление определенного десантника
     */
    private String getMarineString(SpaceMarine m, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMARINE. num. :").append(index).append(" (ID: ").append(m.getId()).append(")\n");
        sb.append("   ├─ Name: ").append(m.getName()).append("\n");
        sb.append("   ├─ Coordinates: (").append(m.getCoordinates().getX()).append(", ").append(m.getCoordinates().getY()).append(")\n");
        sb.append("   ├─ Created: ").append(m.getCreationDate()).append("\n");
        sb.append("   ├─ Health: ").append(m.getHealth()).append("\n");
        sb.append("   ├─ Achievements: ").append(m.getAchievements() != null ? m.getAchievements() : "null").append("\n");
        sb.append("   ├─ Category: ").append(m.getCategory() != null ? m.getCategory() : "null").append("\n");
        sb.append("   ├─ Weapon: ").append(m.getWeaponType() != null ? m.getWeaponType() : "null").append("\n");
        sb.append("   └─ Chapter: ").append(m.getChapter().getName())
                .append(" (count: ").append(m.getChapter().getMarinesCnt())
                .append(", world: ").append(m.getChapter().getWorld() != null ? m.getChapter().getWorld() : "null").append(")\n");
        return sb.toString();
    }
}