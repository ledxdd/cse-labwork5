package cse_labwork5.src.services;

import cse_labwork5.src.models.SpaceMarine;
import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Управляет коллекцией десантников
 * <p> Обеспечивает основные команды для управления коллекцией, такие как: добавление нового десантника, удаление, очистка коллекции
 * получение данных о коллекции, изменение состояние коллекции</p>
 */
public class CollectionManager {
    private final TreeSet<SpaceMarine> collection;
    private final LocalDateTime initDate;
    private double maxHealth = -10000D;
    private double minHealth = 10000D;

    /**
     * Создает новый менеджер коллекции с пустой коллекцией и текущей датой инициализации
     */
    public CollectionManager() {
        this.collection = new TreeSet<>();
        this.initDate = LocalDateTime.now();
    }

    /**
     * Добавляет десантника в коллекцию и обновляет граничные значения здоровья
     * @param marine десантник для добавления
     */
    public void add(SpaceMarine marine) {
        collection.add(marine);
        updateHealthBounds(marine);
    }

    /**
     * Удаляет десантника из коллекции и пересчитывает граничные значения здоровья
     * @param marine десантник для удаления
     */
    public void remove(SpaceMarine marine) {
        collection.remove(marine);
        recalculateHealthBounds();
    }

    /**
     * Удаляет десантника по идентификатору
     * @param id идентификатор десантника
     * @return true если десантник был удален, false если десантник с таким id не найден
     */
    public boolean removeById(long id) {
        return collection.removeIf(m -> m.getId() == id);
    }

    /**
     * Очищает коллекцию и сбрасывает граничные значения здоровья
     */
    public void clear() {
        collection.clear();
        maxHealth = -10000D;
        minHealth = 10000D;
    }

    /**
     * Находит десантника по идентификатору
     * @param id идентификатор десантника
     * @return десантник с указанным id или null если не найден
     */
    public SpaceMarine findById(long id) {
        for (SpaceMarine m : collection) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Вычисляет среднее арифметическое здоровья всех десантников в коллекции
     * @return среднее значение здоровья
     */
    public double getAverageHealth() {
        double res = 0d;
        int cnt = 0;

        for (SpaceMarine m : collection) {
            res += m.getHealth();
            cnt++;
        }

        return res / cnt;
    }

    /**
     * Подсчитывает количество десантников, у которых количество бойцов в главе меньше указанного значения
     * @param marinesCount пороговое значение количества десантников в главе
     * @return количество десантников, удовлетворяющих условию
     */
    public long countLessThanChapter(int marinesCount) {
        long cnt = 0;

        for (SpaceMarine m : collection) {
            if (m.getChapter().getMarinesCnt() < marinesCount) {
                cnt++;
            }
        }

        return cnt;
    }

    /**
     * Удаляет всех десантников с здоровьем ниже указанного значения
     * @param health пороговое значение здоровья
     */
    public void removeLower(double health) {
        collection.removeIf(m -> m.getHealth() < health);
    }

    /**
     * Проверяет, превышает ли указанное здоровье максимальное значение в коллекции
     * @param health значение здоровья для проверки
     * @return true если здоровье больше максимального, false в противном случае
     */
    public boolean isGreaterThanMax(double health) {
        return health > maxHealth;
    }

    /**
     * Проверяет, меньше ли указанное здоровье минимального значения в коллекции
     * @param health значение здоровья для проверки
     * @return true если здоровье меньше минимального, false в противном случае
     */
    public boolean isLessThanMin(double health) {
        return health < minHealth;
    }

    /**
     * Обновляет граничные значения здоровья на основе добавленного десантника
     * @param marine добавленный десантник
     */
    private void updateHealthBounds(SpaceMarine marine) {
        double health = marine.getHealth();
        if (health > maxHealth) maxHealth = health;
        if (health < minHealth) minHealth = health;
    }

    /**
     * Пересчитывает граничные значения здоровья по всем элементам коллекции
     */
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

    /**
     * Возвращает коллекцию десантников
     * @return коллекция десантников в виде TreeSet
     */
    public TreeSet<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Возвращает дату инициализации менеджера коллекции
     * @return дата создания менеджера
     */
    public LocalDateTime getInitDate() {
        return initDate;
    }

    /**
     * Возвращает количество элементов в коллекции
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }

    /**
     * Проверяет, пуста ли коллекция
     * @return true если коллекция пуста, false в противном случае
     */
    public boolean isEmpty() {
        return collection.isEmpty();
    }
}