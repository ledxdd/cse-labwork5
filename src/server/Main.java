package cse_labwork5.src.server;

import cse_labwork5.src.common.services.CollectionManager;
import cse_labwork5.src.server.commands.CommandExecutor;
import cse_labwork5.src.server.network.TCPServer;

public class Main {
    public static void main(String[] args) {
        int port = 12345;

        CollectionManager collectionManager = new CollectionManager();

        CommandExecutor commandExecutor = new CommandExecutor(collectionManager);

        TCPServer server = new TCPServer(port, commandExecutor);
        server.start();
    }
}
