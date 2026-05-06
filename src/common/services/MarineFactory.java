package common.services;

import common.models.*;

import java.io.*;
import java.util.Scanner;

/**
 * Фабрика для создания объектов
 * <p> Предоставляет методы для создания объектов коллекции, а также для загрузки данных из файлов .xml </p>
 */
public class MarineFactory {
    private final Scanner scanner;

    /**
     * Создает фабрику с указанным источником ввода.
     *
     * @param scanner источник ввода для чтения данных
     */

    public MarineFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Создает десантника из файла, читая данные построчно.
     *
     * @param path путь к файлу (не используется)
     * @param br буферизованный читатель для чтения данных
     * @return созданный десантник или {@code null} при ошибке
     * @throws IOException если произошла ошибка чтения
     */
    public SpaceMarine createMarineFromFile(String path, BufferedReader br) throws IOException {
        SpaceMarine marine = new SpaceMarine("update");
        Coordinates crdnts = new Coordinates();
        Chapter chapter = new Chapter();

        String line = br.readLine();
        marine.setName(line);
        double x = Double.parseDouble(br.readLine());
        if (Double.isInfinite(x) || x > Double.MAX_VALUE) {
            System.out.println("Координата слишком большая. Невозможно добавить десантника.");
            return null;
        }
        crdnts.setX(x);
        double y = Double.parseDouble(br.readLine());
        if (Double.isInfinite(y) || y > Double.MAX_VALUE) {
            System.out.println("Координата слишком большая. Невозможно добавить десантника.");
            return null;
        }
        crdnts.setY((float) y);

        marine.setCoordinates(crdnts);
        marine.setHealth(Double.valueOf(br.readLine()));

        marine.setAchievments(br.readLine());
        marine.setCategory(AstartesCategory.valueOf(br.readLine()));

        marine.setWeaponType(Weapon.valueOf(br.readLine()));
        chapter.setName(br.readLine());
        chapter.setMarinesCount(Integer.parseInt(br.readLine()));
        chapter.setWorld(br.readLine());

        marine.setChapter(chapter);

        return marine;
    }

    /**
     * Создает десантника из консольного ввода.
     *
     * <p>Запрашивает у пользователя все необходимые поля:
     * <ul>
     *   <li>имя (обязательное)</li>
     *   <li>координаты X и Y</li>
     *   <li>здоровье (может быть null)</li>
     *   <li>достижения (может быть null)</li>
     *   <li>категория (может быть null)</li>
     *   <li>тип оружия (может быть null)</li>
     *   <li>глава ордена (обязательная)</li>
     * </ul>
     *
     * @param mode режим создания: "creation" — новый десантник, "update" — обновление
     * @return созданный десантник
     */
    public SpaceMarine createMarine(String mode) {
        if ("creation".equals(mode)) {
            System.out.println("*******| Создание нового десантника |*******");
        } else {
            System.out.println("*******| Обновление данных десантника |*******");
        }

        SpaceMarine marine = new SpaceMarine();

        setName(marine);
        setCoordinates(marine);
        setHealth(marine);
        setAchievements(marine);
        setCategory(marine);
        setWeapon(marine);
        setChapter(marine);

        return marine;
    }

    public Chapter createChapter() {
        String name;
        while (true) {
            System.out.println("Введите название главы десантника: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Имя главы не может быть пустым!");
        }

        int count;
        while (true) {
            System.out.println("Введите количество десантников в главе (1-1000): ");
            String input = scanner.nextLine().trim();
            try {
                count = Integer.parseInt(input);
                if (count > 0 && count <= 1000) break;
                System.out.println("Число должно быть между 1 и 1000.");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
        }

        System.out.println("Введите мир главы десантника: ");
        String world = scanner.nextLine().trim();

        return new Chapter(name, count, world.isEmpty() ? null : world);
    }
    /**
     * Запрашивает и устанавливает имя десантника.
     *
     * @param marine объект для установки имени
     */
    private void setName(SpaceMarine marine) {
        while (true) {
            System.out.println("Введите имя десантника: ");
            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                marine.setName(name);
                return;
            }
            System.out.println("Имя не может быть пустым. Попробуйте снова.");
        }
    }

    /**
     * Запрашивает и устанавливает координаты десантника.
     *
     * @param marine объект для установки координат
     */
    private void setCoordinates(SpaceMarine marine) {
        Coordinates coordinates = new Coordinates();

        while (true) {
            System.out.println("Введите х координату: ");
            String input = scanner.nextLine().trim();
            try {
                double x = Double.parseDouble(input);
                if (Double.isInfinite(x) || x > Double.MAX_VALUE) {
                    System.out.println("Неверный формат ввода, число слишком большое!");
                } else {
                    coordinates.setX(x);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
        }

        while (true) {
            System.out.println("Введите у координату: ");
            String input = scanner.nextLine().trim();
            try {
                double y = Double.parseDouble(input);
                if (Double.isInfinite(y) || y > Double.MAX_VALUE) {
                    System.out.println("неверный формат ввода, число слишком большое!");
                }
                coordinates.setY((float) y);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
        }

        marine.setCoordinates(coordinates);
    }

    /**
     * Запрашивает и устанавливает здоровье десантника.
     *
     * <p>Пустой ввод устанавливает значение {@code null}.
     *
     * @param marine объект для установки здоровья
     */

    private void setHealth(SpaceMarine marine) {
        while (true) {
            System.out.println("Введите здоровье десантника: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                marine.setHealth(null);
                return;
            }

            try {
                double health = Double.parseDouble(input);
                if (health > 0) {
                    marine.setHealth(health);
                    return;
                }
                System.out.println("Здоровье должно быть больше 0!");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
        }
    }

    /**
     * Запрашивает и устанавливает достижения десантника.
     *
     * <p>Пустой ввод устанавливает значение {@code null}.
     *
     * @param marine объект для установки достижений
     */
    private void setAchievements(SpaceMarine marine) {
        System.out.println("Введите достижения десантника: ");
        String achieves = scanner.nextLine().trim();
        marine.setAchievments(achieves.isEmpty() ? null : achieves);
    }

    /**
     * Запрашивает и устанавливает категорию десантника.
     *
     * <p>Пустой ввод устанавливает значение {@code null}.
     *
     * @param marine объект для установки категории
     */
    private void setCategory(SpaceMarine marine) {
        while (true) {
            System.out.println("Введите категорию десантника: ");
            System.out.println("1.SCOUT,\n2.AGGRESSOR,\n3.TACTICAL,\n4.APOTHECARY");

            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                marine.setCategory(null);
                return;
            }

            try {
                AstartesCategory category = AstartesCategory.valueOf(input);
                marine.setCategory(category);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод! Попробуйте снова! (Введите 'help' для отображения доступных команд)");
            }
        }
    }

    /**
     * Запрашивает и устанавливает тип оружия десантника.
     *
     * <p>Пустой ввод устанавливает значение {@code null}.
     *
     * @param marine объект для установки типа оружия
     */
    private void setWeapon(SpaceMarine marine) {
        while (true) {
            System.out.println("Введите оружие десантника: ");
            System.out.println("1.MELTAGUN,\n2.BOLT_RIFLE,\n3.PLASMA_GUN,\n4.GRENADE_LAUNCHER");

            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                marine.setWeaponType(null);
                return;
            }

            try {
                Weapon weapon = Weapon.valueOf(input);
                marine.setWeaponType(weapon);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод! Попробуйте снова");
            }
        }
    }

    /**
     * Запрашивает и устанавливает главу ордена десантника.
     *
     * <p>Запрашивает название главы, количество десантников (1-1000) и мир.
     *
     * @param marine объект для установки главы
     */
    private void setChapter(SpaceMarine marine) {
        String name;
        while (true) {
            System.out.println("Введите название главы десантника: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Имя главы не может быть пустым!");
        }

        int count;
        while (true) {
            System.out.println("Введите количество десантников в главе (1-1000): ");
            String input = scanner.nextLine().trim();
            try {
                count = Integer.parseInt(input);
                if (count > 0 && count <= 1000) break;
                System.out.println("Число должно быть между 1 и 1000.");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода!");
            }
        }

        System.out.println("Введите мир главы десантника: ");
        String world = scanner.nextLine().trim();

        marine.setChapter(new Chapter(name, count, world.isEmpty() ? null : world));
    }

    /**
     * Загружает коллекцию десантников из XML-файла.
     *
     * <p>Читает XML-файл, парсит каждый элемент {@code <spacemarine>}
     * и добавляет десантников в коллекцию.
     *
     * @param filePath путь к XML-файлу
     * @param collectionManager менеджер коллекции для добавления десантников
     */
    public static void loadDataFromXML(String filePath, CollectionManager collectionManager) {
        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder currentMarine = new StringBuilder();
            boolean readingMarine = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.contains("<spacemarine>")) {
                    readingMarine = true;
                    currentMarine = new StringBuilder();
                    currentMarine.append(line).append("\n");
                }
                else if (line.contains("</spacemarine>")) {
                    currentMarine.append(line).append("\n");
                    readingMarine = false;

                    SpaceMarine marine = parseSpaceMarine(currentMarine.toString());
                    if (marine != null) {

                        collectionManager.add(marine);
                    }
                }
                else if (readingMarine) {
                    currentMarine.append(line).append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Парсит XML-строку и создает объект {@link SpaceMarine}.
     *
     * @param marineXml XML-строка с данными десантника
     * @return созданный десантник или {@code null} при ошибке
     */
    public static SpaceMarine parseSpaceMarine(String marineXml) {
        try {
            SpaceMarine marine = new SpaceMarine("update");

            String idStr = extractValue(marineXml, "id");
            if (idStr != null) marine.setId((long) Integer.parseInt(idStr));

            String nameStr = extractValue(marineXml, "name");
            if (nameStr != null) marine.setName(nameStr);

            String xStr = extractValue(marineXml, "x");
            String yStr = extractValue(marineXml, "y");
            if (xStr != null && yStr != null) {
                Coordinates coords = new Coordinates();
                double x = Double.parseDouble(xStr);

                if (Double.isInfinite(x)) {
                    System.out.println("Число слишком большое для координаты! Не удалось добавить десантника в коллекцию из файла");
                    return null;
                }
                coords.setX(x);
                double y = Double.parseDouble(yStr);

                if (Double.isInfinite(y)) {
                    System.out.println("Число слишком большое для координаты! Не удалось добавить десантника в коллекцию из файла");
                    return null;
                }
                coords.setY((float) y);
                marine.setCoordinates(coords);
            }

            String healthStr = extractValue(marineXml, "health");
            if (healthStr != null) marine.setHealth(Double.parseDouble(healthStr));

            String achievementsStr = extractValue(marineXml, "achievements");
            if (achievementsStr != null) marine.setAchievements(achievementsStr);

            String categoryStr = extractValue(marineXml, "category");
            if (categoryStr != null) marine.setCategory(AstartesCategory.valueOf(categoryStr));

            String weaponStr = extractValue(marineXml, "weapon");
            if (weaponStr != null) marine.setWeapon(weaponStr);

            Chapter chapter = new Chapter();
            String chapterName = extractValue(marineXml, "name", 1);
            String marinesCount = extractValue(marineXml, "marinesCount");
            String world = extractValue(marineXml, "world");

            if (chapterName != null) chapter.setName(chapterName);
            if (marinesCount != null) chapter.setMarinesCount(Integer.parseInt(marinesCount));
            if (world != null) chapter.setWorld(world);

            marine.setChapter(chapter);

            return marine;

        } catch (Exception e) {
            System.out.println("Ошибка при парсинге: " + e.getMessage());
            return null;
        }
    }

    /**
     * Извлекает значение тега из XML-строки (первое вхождение).
     *
     * @param xml XML-строка
     * @param tagName имя тега
     * @return значение тега или {@code null} если тег не найден
     */
    private static String extractValue(String xml, String tagName){
        return extractValue(xml, tagName, 0);
    }

    /**
     * Извлекает значение тега из XML-строки по номеру вхождения.
     *
     * @param xml XML-строка
     * @param tagName имя тега
     * @param occurrence номер вхождения (0 — первое)
     * @return значение тега или {@code null} если тег не найден
     */
    public static String extractValue(String xml, String tagName, int occurrence) {
        String openTag = "<" + tagName + ">";
        String closeTag = "</" + tagName + ">";

        int startIndex = 0;
        for (int i = 0; i <= occurrence; i++) {
            startIndex = xml.indexOf(openTag, startIndex);
            if (startIndex == -1) return null;
            if (i < occurrence) {
                startIndex += openTag.length();
            }
        }

        int endIndex = xml.indexOf(closeTag, startIndex);
        if (endIndex == -1) return null;

        return xml.substring(startIndex + openTag.length(), endIndex).trim();
    }
}