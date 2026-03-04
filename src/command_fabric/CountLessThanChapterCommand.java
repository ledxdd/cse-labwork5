package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import java.util.Scanner;

public class CountLessThanChapterCommand implements Command {
    private final CollectionManager collectionManager;
    private final Scanner scanner = new Scanner(System.in);

    public CountLessThanChapterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {
        int count;

        if (arg != null && !arg.trim().isEmpty()) {
            try {
                count = Integer.parseInt(arg.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Marines count must be a number.");
                return;
            }
        } else {
            System.out.println("Enter chapter marines count: ");
            try {
                count = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
                return;
            }
        }

        long result = collectionManager.countLessThanChapter(count);
        System.out.println("Counter: " + result);
    }

}