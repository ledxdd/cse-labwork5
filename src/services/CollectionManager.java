package cse_labwork5.src.services;

import cse_labwork5.src.models.SpaceMarine;
import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Управляет коллекцией десантников
 *
 * <p> Обеспечивает основные команды для управления коллекцией, такие как: добавление нового десантника, удаление, очистка коллекции
 * получение данных о коллекции, изменение состояние коллекции</p>
 */
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
        for (SpaceMarine m : collection) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public double getAverageHealth() {
        double res = 0d;
        int cnt = 0;

        for (SpaceMarine m : collection) {
            res += m.getHealth();
            cnt++;
        }

        return res / cnt;
    }

    public long countLessThanChapter(int marinesCount) {
        long cnt = 0;

        for (SpaceMarine m : collection) {
            if (m.getChapter().getMarinesCnt() < marinesCount) {
                cnt++;
            }
        }

        return cnt;
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
        double mxh = -1000000;
        double mh = 1000000;

        for (SpaceMarine m : collection) {
            if (m.getHealth() > mxh) mxh = m.getHealth();
        }

        for (SpaceMarine m : collection) {
            if (m.getHealth() < mh) mh = m.getHealth();
        }

        minHealth = mh;
        maxHealth = mxh;
    }

    // Getters
    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }

    public LocalDateTime getInitDate() {
        return initDate;
    }

    public int size() {
        return collection.size();
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }
}