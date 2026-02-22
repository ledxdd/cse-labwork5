package cse_labwork5.src.services;

import cse_labwork5.src.models.SpaceMarine;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class CollectionManager {
    private final TreeSet<SpaceMarine> collection;
    private final LocalDateTime initDate;
    private double maxHealth = -10000D;
    private double minHealth = 10000D;

    public CollectionManager() {
        this.collection = new TreeSet<>();
        this.initDate = LocalDateTime.now();
    }

    public void add(SpaceMarine marine) {
        collection.add(marine);
        updateHealthBounds(marine);
    }

    public void remove(SpaceMarine marine) {
        collection.remove(marine);
        recalculateHealthBounds();
    }

    public boolean removeById(long id) {
        return collection.removeIf(m -> m.getId() == id);
    }

    public void clear() {
        collection.clear();
        maxHealth = -10000D;
        minHealth = 10000D;
    }

    public SpaceMarine findById(long id) {
        return collection.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public double getAverageHealth() {
        return collection.stream()
                .mapToDouble(SpaceMarine::getHealth)
                .average()
                .orElse(0.0);
    }

    public long countLessThanChapter(int marinesCount) {
        return collection.stream()
                .filter(m -> m.getChapter().getMarinesCnt() < marinesCount)
                .count();
    }

    public void removeLower(double health) {
        collection.removeIf(m -> m.getHealth() < health);
    }

    public boolean isGreaterThanMax(double health) {
        return health > maxHealth;
    }

    public boolean isLessThanMin(double health) {
        return health < minHealth;
    }

    private void updateHealthBounds(SpaceMarine marine) {
        double health = marine.getHealth();
        if (health > maxHealth) maxHealth = health;
        if (health < minHealth) minHealth = health;
    }

    private void recalculateHealthBounds() {
        maxHealth = collection.stream()
                .mapToDouble(SpaceMarine::getHealth)
                .max()
                .orElse(-10000D);
        minHealth = collection.stream()
                .mapToDouble(SpaceMarine::getHealth)
                .min()
                .orElse(10000D);
    }

    // Getters
    public TreeSet<SpaceMarine> getCollection() { return collection; }
    public LocalDateTime getInitDate() { return initDate; }
    public int size() { return collection.size(); }
    public boolean isEmpty() { return collection.isEmpty(); }
}