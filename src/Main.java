package cse_labwork5.src;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.command_fabric.CommandExecutor;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CollectionManager collectionManager = new CollectionManager();
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor(collectionManager, scanner);

        System.out.println("Enter 'help' to see available commands:");

        while (executor.isRunning()) {
            System.out.print('>');
            String userInput = scanner.nextLine();
            executor.execute(userInput);
        }

        scanner.close();
    }
}