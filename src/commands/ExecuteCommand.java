package cse_labwork5.src.commands;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

import java.io.*;
import java.nio.Buffer;

public class ExecuteCommand implements Command{
    MarineFactory marineFactory;
    CommandExecutor executor;
    CollectionManager collectionManager;
    public ExecuteCommand(CommandExecutor commandExecutor, MarineFactory marineFactory, CollectionManager collectionManager) {
        this.executor = commandExecutor;
        this.marineFactory = marineFactory;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) throws IOException {
        String path = arg.trim();
        File script = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(script));
        String line;
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            lineNumber++;
            line = line.trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            System.out.println("Executing script " + line);
            if ("add".equals(line)) {
                SpaceMarine marine = marineFactory.createMarineFromFile(path, br);
                collectionManager.add(marine);
            } else {
                executor.execute(line);
            }
        }
    }
}
