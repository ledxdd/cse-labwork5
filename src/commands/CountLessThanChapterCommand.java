package cse_labwork5.src.commands;

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
        System.out.println("Enter chapter marines count: ");
        try {
            int count = scanner.nextInt();
            scanner.nextLine(); // consume newline

            long result = collectionManager.countLessThanChapter(count);
            System.out.println("Counter: " + result);
        } catch (Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }
}