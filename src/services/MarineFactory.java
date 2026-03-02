package cse_labwork5.src.services;

import cse_labwork5.src.models.*;

import java.io.*;
import java.nio.Buffer;
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
            String name = scanner.nextLine();

            if (isValidName(name)) {
                marine.setName(name);
                break;
            }
            System.out.println("Name can't be blank. Please, enter again: ");
        }
    }

    private boolean isValidName(String name) {
        return !(name == null || name.trim().isEmpty());
    }

    private void setCoordinates(SpaceMarine marine) {
        Coordinates coordinates = new Coordinates();

        System.out.println("Enter x coordinate: ");
        int x = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter y coordinate: ");
        int y = scanner.nextInt();
        scanner.nextLine();

        coordinates.setX(x);
        coordinates.setY(y);
        marine.setCoordinates(coordinates);
    }

    private void setHealth(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's health: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                marine.setHealth(null);
                break;
            }

            try {
                Double health = Double.valueOf(input);
                if (marine.setHealth(health)) break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            }
        }
    }

    private void setAchievements(SpaceMarine marine) {
        System.out.println("Enter marine's achievements: ");
        String achieves = scanner.nextLine();
        marine.setAchievments(achieves.isEmpty() ? null : achieves);
    }

    private void setCategory(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's category:");
            System.out.println("1. SCOUT\n2. AGGRESSOR\n3. TACTICAL\n4. APOTHECARY");

            String input = scanner.nextLine().toUpperCase().trim();

            if (input.isEmpty()) {
                marine.setCategory(null);
                break;
            }

            switch (input) {
                case "SCOUT": case "1":
                    marine.setCategory(AstartesCategory.SCOUT);
                    return;
                case "AGGRESSOR": case "2":
                    marine.setCategory(AstartesCategory.AGGRESSOR);
                    return;
                case "TACTICAL": case "3":
                    marine.setCategory(AstartesCategory.TACTICAL);
                    return;
                case "APOTHECARY": case "4":
                    marine.setCategory(AstartesCategory.APOTHECARY);
                    return;
                default:
                    System.out.println("Wrong input! Try again!");
            }
        }
    }

    private void setWeapon(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's weapon:");
            System.out.println("1. MELTAGUN\n2. BOLT_RIFLE\n3. PLASMA_GUN\n4. GRENADE_LAUNCHER");

            String input = scanner.nextLine().toUpperCase().trim();

            if (input.isEmpty()) {
                marine.setWeaponType(null);
                break;
            }

            switch (input) {
                case "MELTAGUN": case "1":
                    marine.setWeaponType(Weapon.MELTAGUN);
                    return;
                case "BOLT_RIFLE": case "2":
                    marine.setWeaponType(Weapon.BOLT_RIFLE);
                    return;
                case "PLASMA_GUN": case "3":
                    marine.setWeaponType(Weapon.PLASMA_GUN);
                    return;
                case "GRENADE_LAUNCHER": case "4":
                    marine.setWeaponType(Weapon.GRENADE_LAUNCHER);
                    return;
                default:
                    System.out.println("Wrong input! Try again!");
            }
        }
    }

    private void setChapter(SpaceMarine marine) {
        System.out.println("Enter marine's chapter name: ");
        String name = scanner.nextLine();

        int count = 0;
        while (true) {
            System.out.println("Enter marine's chapter marinesCount: ");
            try {
                count = scanner.nextInt();
                scanner.nextLine();
                if (count > 0 && count <= 1000) break;
                System.out.println("Count must be between 1 and 1000!");
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }

        System.out.println("Enter marine's chapter world: ");
        String world = scanner.nextLine();

        marine.setChapter(new Chapter(name, count, world));
    }
}