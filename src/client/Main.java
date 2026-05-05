package cse_labwork5.src.client;

import cse_labwork5.src.client.commands.CommandExecutor;
import cse_labwork5.src.client.network.TCPClient;
import cse_labwork5.src.common.Request;
import cse_labwork5.src.common.Response;
import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.common.services.MarineFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost", 12345);
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
                } else {
                    System.out.println("Команда не найдена в регистре.");
                }

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
