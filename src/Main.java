package cse_labwork5.src;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.command_fabric.CommandExecutor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static cse_labwork5.src.services.MarineFactory.loadDataFromXML;

public class Main {
    public static void main(String[] args) throws IOException {
        CollectionManager collectionManager = new CollectionManager();
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor(collectionManager, scanner);

        loadDataFromXML("data/data.xml", collectionManager);

        System.out.println("Добро пожаловать в менеджер коллекции космических десантиников: ");
        System.out.println("Введите 'help' для отображения доступных команд. ");

        while (executor.isRunning()) {
            System.out.print('>');
            String userInput = scanner.nextLine();
            executor.execute(userInput);
        }

        scanner.close();
    }
}