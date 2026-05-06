package server;

import common.services.CollectionManager;
import server.commands.CommandExecutor;
import server.network.TCPServer;
import server.services.CollectionFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int port = 58110;

        CollectionManager collectionManager = new CollectionManager();
        String fileEnv = System.getenv("COLLECTION_FILE");
        Path collectionPath = Path.of(fileEnv == null || fileEnv.isBlank() ? "data/collection.xml" : fileEnv);
        CollectionFileService fileService = new CollectionFileService(collectionPath);
        try {
            fileService.load(collectionManager);
            logger.info("Коллекция загружена из файла: {}", collectionPath);
        } catch (Exception e) {
            logger.error("Не удалось загрузить коллекцию из файла {}", collectionPath, e);
        }

        CommandExecutor commandExecutor = new CommandExecutor(collectionManager, fileService);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                fileService.save(collectionManager);
                logger.info("Коллекция сохранена при завершении работы.");
            } catch (Exception e) {
                logger.error("Ошибка автосохранения коллекции", e);
            }
        }));

        TCPServer server = new TCPServer(port, commandExecutor);
        logger.info("Запуск серверного приложения на порту {}", port);
        server.start();
    }
}
