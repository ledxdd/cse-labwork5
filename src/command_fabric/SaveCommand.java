package cse_labwork5.src.command_fabric;

import cse_labwork5.src.models.SpaceMarine;
import cse_labwork5.src.services.CollectionManager;
import cse_labwork5.src.services.MarineFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements Command {
    CollectionManager collectionManager;
    public SaveCommand(MarineFactory marineFactory, CollectionManager collectionManager) throws IOException {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) throws IOException {

        final String PATH = "data/data.xml";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bw.write("<collection>\n");
            for (SpaceMarine m : collectionManager.getCollection()) {
                bw.write("    <spacemarine>\n");
                bw.write("        <id>" + m.getId() + "</id>\n");
                bw.write("        <name>" + m.getName() + "</name>\n");
                bw.write("        <coordinates>\n");
                bw.write("            <x>" + m.getCoordinates().getX() + "</x>\n");
                bw.write("            <y>" + m.getCoordinates().getY() + "</y>\n");
                bw.write("        </coordinates>\n");
                bw.write("        <health>" + m.getHealth() + "</health>\n");
                bw.write("        <achievements>" + m.getAchievements() + "</achievements>\n");
                bw.write("        <category>" + m.getCategory() + "</category>\n");
                bw.write("        <weapon>" + m.getWeaponType() + "</weapon>\n");
                bw.write("        <chapter>\n");
                bw.write("            <name>" + m.getChapter().getName() + "</name>\n");
                bw.write("            <marinesCount>" + m.getChapter().getMarinesCnt() + "</marinesCount>\n");
                bw.write("            <world>" + m.getChapter().getWorld() + "</world>\n");
                bw.write("        </chapter>\n");
                bw.write("</spacemarine>\n");
            }
            bw.write("</collection>\n");
        }
    }
}