package client;

import client.commands.CommandExecutor;
import client.network.TCPClient;
import common.Request;
import common.Response;
import common.services.MarineFactory;

import java.util.Scanner;

/**
 * Основной класс клиента
 *
 * <p>Осуществляет </p>
 */

public class Main {
    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost", 58110);
        Scanner scanner = new Scanner(System.in);

        MarineFactory marineFactory = new MarineFactory(scanner);
        CommandExecutor executor = new CommandExecutor(marineFactory);

        System.out.println("Клиент запущен. Введите команду: ");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                Request request = executor.buildRequest(input);

                if (input.startsWith("help")) {
                    continue;
                }

                if (request != null) {
                    Response response = client.sendAndRecieve(request);
                    System.out.println(response.getMessage());
                    if (response.getCollection() != null) {
                        if (response.getCollection().isEmpty()) {
                            System.out.println("Коллекция пуста.");
                        } else {
                            response.getCollection().forEach(System.out::println);
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
