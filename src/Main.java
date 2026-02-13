package cse_labwork5.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static public class SpaceMarine implements Comparable<SpaceMarine> {
        private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private Double health; //Поле может быть null, Значение поля должно быть больше 0
        private String achievements; //Поле может быть null
        private AstartesCategory category; //Поле может быть null
        private Weapon weaponType; //Поле может быть null
        private Chapter chapter; //Поле не может быть null

        static private long next_id = 0L;

        private void setName(String name) {
            if (name == null || " ".equals(name) || name.trim().isEmpty()) {
                System.out.println("Name can't be empty. Please enter the valid name!");
            } else {
                this.name = name;
            }
        }

        public SpaceMarine() {
            this.id = next_id++;
        }

        public SpaceMarine(String name, Coordinates crdnt, double health, String achievements, AstartesCategory category, Weapon weapon, Chapter chapter) {
            this.id = next_id++;
            setName(name);
            this.coordinates.x = crdnt.x;
            this.coordinates.y = crdnt.y;
            this.creationDate = java.time.ZonedDateTime.now();
            this.health = health;
            this.achievements = achievements;
            this.category = category;
            this.weaponType = weapon;
            this.chapter = chapter;
        }

        @Override
        public int compareTo(SpaceMarine other) {
            return this.id.compareTo(other.id);
        }


    }

    static public class Coordinates {
        private Double x; //Поле не может быть null
        private float y;
    }

    static public class Chapter {
        private String name; //Поле не может быть null, Строка не может быть пустой
        private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
        private String world; //Поле может быть null

        Chapter(String name, int marinescnt, String wrld) {
            this.name = name;
            this.marinesCount = marinescnt;
            this.world = wrld;
        }
    }

    public enum AstartesCategory {
        SCOUT,
        AGGRESSOR,
        TACTICAL,
        APOTHECARY;
    }
    
    public enum Weapon {
        MELTAGUN,
        BOLT_RIFLE,
        PLASMA_GUN,
        GRENADE_LAUNCHER;
    }

    static private TreeSet<SpaceMarine> collection;
    static private BufferedReader bReader;
    static private BufferedWriter bWriter;
    static private Scanner global_scanner;

    static public void printHelp() {
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} : добавить новый элемент в коллекцию");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");        
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("average_of_health : вывести среднее значение поля health для всех элементов коллекции");
        System.out.println("count_less_than_chapter chapter : вывести количество элементов, значение поля chapter которых меньше заданного");
        System.out.println("print_field_ascending_achievements : вывести значения поля achievements всех элементов в порядке возрастания");
    }

    static double getAvHealth() {
        double result = 0;
        int cnt = 0;
        
        for (SpaceMarine spaceMarine : collection) {
            cnt++;
            result = result + spaceMarine.health;
        }
        return result / (double)cnt;
    }

    static void clearCollection(TreeSet<SpaceMarine> collection) {
        if (!collection.isEmpty()) {
            collection.clear();
            System.out.println("Collection cleared!");
        } else {
            System.out.println("Nothing to clear. Collection is already expty!");
        }
    }

    static void runProgramm(TreeSet<SpaceMarine> collection) {
        boolean is_running = true;
        
        System.out.println("Enter 'help' to see available commands:");
        while (is_running)
        {
            System.out.print('>');
            
            
            String user_input;
            user_input = global_scanner.nextLine();
            
            if (null != user_input) switch (user_input) {
                case "exit" -> is_running = false;
                case "help" -> printHelp();
                case "clear()" -> {
                    for (int i = 0; i < 1200; i++) {
                        System.out.println(" ");
                    }
                }
                case "average_of_health" -> {
                    double av_health = getAvHealth();
                    System.out.println(av_health);
                }
                case "clear" -> clearCollection(collection);
                case "add" -> fillMarine();
                default -> {
                }
            }
        } 
        
    }

    static boolean checkName(String name) {
        return !(name == null || " ".equals(name) || name.trim().isEmpty());
    }

    static void fillMarine() {
        SpaceMarine marine = new SpaceMarine();
        
        System.out.println("*******| New marine creation |*******");
        
        boolean creating_marine = true;
        
        while (creating_marine) {
            System.out.println("Enter the marine name: ");
            String name = global_scanner.nextLine();
            
            if (checkName(name)) {
                marine.setName(name);
                creating_marine = false;
            } else {
                System.out.println("Name can't be blank. Please, enter again: ");
            }
            
        }
        
    }

    static void fillCollection(TreeSet<SpaceMarine> collection) {
        boolean collecting_info = true;

        while (collecting_info) {
            
        }
    }

    public static void main(String[] args) {
        collection = new TreeSet<>();
        global_scanner = new Scanner(System.in);
        
        runProgramm(collection);
    }
}
