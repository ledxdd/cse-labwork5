package server.services;

import common.models.SpaceMarine;
import common.services.CollectionManager;
import common.services.MarineFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CollectionFileService {
    private final Path filePath;

    public CollectionFileService(Path filePath) {
        this.filePath = filePath;
    }

    public void load(CollectionManager collectionManager) throws IOException {

        if (!Files.exists(filePath)) {
            return;
        }

        String xml = Files.readString(filePath, StandardCharsets.UTF_8);
        String[] chunks = xml.split("</spacemarine>");

        for (String chunk : chunks) {

            if (!chunk.contains("<spacemarine>")) {
                continue;
            }

            SpaceMarine marine = MarineFactory.parseSpaceMarine(chunk + "</spacemarine>");
            if (marine != null) {
                collectionManager.add(marine);
            }
        }

        long nextId = collectionManager.getCollection().stream().map(SpaceMarine::getId).max(Long::compareTo).orElse(0L) + 1;
        SpaceMarine.setNextId(nextId);
    }

    public void save(CollectionManager collectionManager) throws IOException {
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }

        String body = collectionManager.getCollection().stream().sorted(Comparator.comparing(SpaceMarine::getName)).map(this::toXml).collect(Collectors.joining("\n"));
        String result = "<collection>\n" + body + "\n</collection>\n";
        Files.writeString(filePath, result, StandardCharsets.UTF_8);
    }

    private String toXml(SpaceMarine marine) {
        return "  <spacemarine>\n" +
                "    <id>" + marine.getId() + "</id>\n" +
                "    <name>" + marine.getName() + "</name>\n" +
                "    <x>" + marine.getCoordinates().getX() + "</x>\n" +
                "    <y>" + marine.getCoordinates().getY() + "</y>\n" +
                "    <health>" + marine.getHealth() + "</health>\n" +
                "    <achievements>" + nullSafe(marine.getAchievements()) + "</achievements>\n" +
                "    <category>" + nullSafe(marine.getCategory()) + "</category>\n" +
                "    <weapon>" + nullSafe(marine.getWeaponType()) + "</weapon>\n" +
                "    <chapter>\n" +
                "      <name>" + marine.getChapter().getName() + "</name>\n" +
                "      <marinesCount>" + marine.getChapter().getMarinesCnt() + "</marinesCount>\n" +
                "      <world>" + nullSafe(marine.getChapter().getWorld()) + "</world>\n" +
                "    </chapter>\n" +
                "  </spacemarine>";
    }

    private String nullSafe(Object value) {
        return value == null ? "" : value.toString();
    }
}
