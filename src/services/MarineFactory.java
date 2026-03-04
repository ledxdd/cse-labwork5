package cse_labwork5.src.services;

import cse_labwork5.src.models.*;

import java.io.*;
import java.util.Scanner;

public class MarineFactory {
    private final Scanner scanner;

    public MarineFactory(Scanner scanner, CollectionManager collectionManager) {
        this.scanner = scanner;
    }

    public SpaceMarine createMarineFromFile(String path, BufferedReader br) throws IOException {
        SpaceMarine marine = new SpaceMarine();
        Coordinates crdnts = new Coordinates();
        Chapter chapter = new Chapter();

        String line = br.readLine();
        marine.setName(line);
        crdnts.setX(Double.parseDouble(br.readLine()));
        crdnts.setY((float) Double.parseDouble(br.readLine()));

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

    public SpaceMarine createMarine(String mode) {
        if ("creation".equals(mode)) {
            System.out.println("*******| New marine creation |*******");
        } else {
            System.out.println("*******| Marine info update |*******");
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

    private void setName(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter the marine name: ");
            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                marine.setName(name);
                return;
            }
            System.out.println("Name can't be blank. Please, enter again.");
        }
    }

    private void setCoordinates(SpaceMarine marine) {
        Coordinates coordinates = new Coordinates();

        while (true) {
            System.out.println("Enter x coordinate: ");
            String input = scanner.nextLine().trim();
            try {
                double x = Double.parseDouble(input);
                coordinates.setX(x);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            }
        }

        while (true) {
            System.out.println("Enter y coordinate: ");
            String input = scanner.nextLine().trim();
            try {
                float y = Float.parseFloat(input);
                coordinates.setY(y);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            }
        }

        marine.setCoordinates(coordinates);
    }

    private void setHealth(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's health: ");
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
                System.out.println("Health must be greater than 0!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            }
        }
    }

    private void setAchievements(SpaceMarine marine) {
        System.out.println("Enter marine's achievements: ");
        String achieves = scanner.nextLine().trim();
        marine.setAchievments(achieves.isEmpty() ? null : achieves);
    }

    private void setCategory(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's category: ");
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
                System.out.println("Wrong input! Try again! (Type 'help' to see list of commands)");
            }
        }
    }

    private void setWeapon(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's weapon: ");
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
                System.out.println("Wrong input! Try again!");
            }
        }
    }

    private void setChapter(SpaceMarine marine) {
        String name;
        while (true) {
            System.out.println("Enter marine's chapter name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Chapter name can't be blank!");
        }

        int count;
        while (true) {
            System.out.println("Enter marine's chapter marinesCount (1-1000): ");
            String input = scanner.nextLine().trim();
            try {
                count = Integer.parseInt(input);
                if (count > 0 && count <= 1000) break;
                System.out.println("Count must be between 1 and 1000.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            }
        }

        System.out.println("Enter marine's chapter world: ");
        String world = scanner.nextLine().trim();

        marine.setChapter(new Chapter(name, count, world.isEmpty() ? null : world));
    }
}