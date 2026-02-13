package cse_labwork5.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static public class SpaceMarine {
        private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private Double health; //Поле может быть null, Значение поля должно быть больше 0
        private String achievements; //Поле может быть null
        private AstartesCategory category; //Поле может быть null
        private Weapon weaponType; //Поле может быть null
        private Chapter chapter; //Поле не может быть null
    }

    static public class Coordinates {
        private Double x; //Поле не может быть null
        private float y;
    }

    static public class Chapter {
        private String name; //Поле не может быть null, Строка не может быть пустой
        private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
        private String world; //Поле может быть null
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
        collection.clear();
    }

    static void runProgramm(TreeSet<SpaceMarine> collection) {
        boolean is_running = true;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter 'help' to see available commands:");
            while (is_running)
            {
                System.out.print('>');
                
                String user_input;
                user_input = scanner.nextLine();
                
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
                    default -> {
                    }
                }
            } 
        }
    }

    static void fillMarine(SpaceMarine marine) {

    }

    static void fillCollection(TreeSet<SpaceMarine> collection) {
        boolean collecting_info = true;

        while (collecting_info) {
            SpaceMarine space_marine = new SpaceMarine();

            fillMarine(space_marine);
        }
    }

    public static void main(String[] args) {
        collection = new TreeSet<>();
        fillCollection(collection);
        
        runProgramm(collection);
    }
}
