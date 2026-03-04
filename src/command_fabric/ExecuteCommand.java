package cse_labwork5.src.command_fabric;

import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

import java.io.*;
import java.util.Scanner;

public class ExecuteCommand implements Command {

    private final CollectionManager collectionManager;

    public ExecuteCommand(CommandExecutor parentExecutor, MarineFactory marineFactory, CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) throws IOException {
        if (arg == null || arg.trim().isEmpty()) {
            System.out.println("Error! No args provided");
            return;
        }

        String filePath = arg.trim();
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();

        if (!file.exists()) {
            System.out.println("Couldn't find the file provided");
            return;
        }
        if (!file.canRead()) {
            System.out.println("No access to file");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder filtered = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                filtered.append(line).append("\n");
            }
            br.close();

            Scanner fileScanner = new Scanner(new StringReader(filtered.toString()));

            CommandExecutor scriptExecutor = new CommandExecutor(collectionManager, fileScanner);

            while (fileScanner.hasNextLine() && scriptExecutor.isRunning()) {
                String fline = fileScanner.nextLine().trim();
                if (fline.isEmpty()) {
                    continue;
                }

                System.out.println("Now executing line: " + fline);
                scriptExecutor.execute(fline);
            }

            fileScanner.close();
            System.out.println("End of the script executing");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (Exception e) {
            System.out.println("Error while executing" + e.getMessage());
        }
    }
}