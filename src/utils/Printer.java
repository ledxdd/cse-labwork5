package cse_labwork5.src.utils;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;

public class Printer {
    private final CollectionManager collectionManager;

    public Printer(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void printHelp() {
        System.out.println("info : вывести информацию о коллекции");
        System.out.println("show : вывести все элементы коллекции");
        System.out.println("add {element} : добавить новый элемент");
        System.out.println("update id {element} : обновить элемент");
        System.out.println("remove_by_id id : удалить элемент по id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : исполнить скрипт");
        System.out.println("exit : завершить программу");
        System.out.println("add_if_max {element} : добавить, если больше максимального");
        System.out.println("add_if_min {element} : добавить, если меньше минимального");
        System.out.println("remove_lower {element} : удалить меньшие элементы");
        System.out.println("average_of_health : среднее значение health");
        System.out.println("count_less_than_chapter chapter : количество элементов с chapter меньше");
        System.out.println("print_field_ascending_achievements : значения achievements по возрастанию");
    }

    public void printInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 COLLECTION INFORMATION");
        System.out.println("=".repeat(50));
        System.out.println("📌 Collection type: TreeSet<SpaceMarine>");
        System.out.println("📅 Initialized: " + collectionManager.getInitDate());
        System.out.println("📦 Elements count: " + collectionManager.size());
        System.out.println("=".repeat(50));
    }

    public void printCollection() {
        if (collectionManager.isEmpty()) {
            System.out.println("📭 Collection is empty!");
            return;
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("📋 COLLECTION CONTENTS (total: " + collectionManager.size() + " marines)");
        System.out.println("=".repeat(100));

        int index = 1;
        for (SpaceMarine m : collectionManager.getCollection()) {
            printMarine(m, index++);
        }
        System.out.println("\n" + "=".repeat(100));
    }

    private void printMarine(SpaceMarine m, int index) {
        System.out.println("\n🔹 MARINE #" + index + " (ID: " + m.getId() + ")");
        System.out.println("   ├─ Name: " + m.getName());
        System.out.println("   ├─ Coordinates: (" + m.getCoordinates().getX() + ", " + m.getCoordinates().getY() + ")");
        System.out.println("   ├─ Created: " + m.getCreationDate());
        System.out.println("   ├─ Health: " + m.getHealth());
        System.out.println("   ├─ Achievements: " + (m.getAchievements() != null ? m.getAchievements() : "null"));
        System.out.println("   ├─ Category: " + (m.getCategory() != null ? m.getCategory() : "null"));
        System.out.println("   ├─ Weapon: " + (m.getWeaponType() != null ? m.getWeaponType() : "null"));
        System.out.println("   └─ Chapter: " + m.getChapter().getName() +
                " (count: " + m.getChapter().getMarinesCnt() +
                ", world: " + (m.getChapter().getWorld() != null ? m.getChapter().getWorld() : "null") + ")");
    }
}