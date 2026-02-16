package cse_labwork5.src;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static private double maximum_collection_health = -10000D;
    static private double minimum_collection_health = 10000D;
    static private TreeSet<SpaceMarine> collection;
    static private LocalDateTime collection_init_date;
    // static private BufferedReader bReader;
    // static private BufferedWriter bWriter;
    static private Scanner global_scanner;
    // static private String PATH_TO_DATA = "/Users/led/Desktop/code/cse_labwork5/data/data.xml";

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

    static public class SpaceMarine implements Comparable<SpaceMarine> {
        private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически !
        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически !
        private Double health; //Поле может быть null, Значение поля должно быть больше 0 !
        private String achievements; //Поле может быть null !
        private AstartesCategory category; //Поле может быть null !
        private Weapon weaponType; //Поле может быть null
        private Chapter chapter; //Поле не может быть null

        static private long next_id = 0L;

        // SETTERS

        public void setNextId(long id) {
            next_id = id;
        }

        public boolean setName(String name) {
            if (name == null || " ".equals(name) || name.trim().isEmpty()) {
                System.out.println("Name can't be empty. Please enter the valid name!");
                return false;
            } else {
                this.name = name;
                return true;
            }
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean setHealth(Double health) {
            if (health != null && health <= 0) {
                System.out.println("Health must be > 0");
                return false;
            }
            this.health = health;
            return true;
        }

        public void setAchievments(String achivs) {
            this.achievements = achivs;
        }

        public void setCategory(AstartesCategory ctg) {
            this.category = ctg;
        }

        public void setCreationDate(java.time.ZonedDateTime time) {
            this.creationDate = time;
        }

        public boolean setChapter(Chapter chpt) {
            if (chpt != null) {
                this.chapter = chpt;
                return true;
            } else {
                System.out.println("Chapter can not be null. Try again!");
            }

            return false;
        }

        public boolean setWeaponType(Weapon wpn_tp) {
            if (wpn_tp != null) {
                this.weaponType = wpn_tp;
                return true;
            } else {
                System.out.println("Weapon type can not be null. Try again!");
                return false;
            }
        }

        public boolean setCoordinates(Coordinates crdnt) {
            if (crdnt != null) {
                this.coordinates = crdnt;
                return true;
            } else {
                System.out.println("Coordinates cant be null. Try again!");
                return false;
            }
        }

        // END OF SETTERS

        // GETTERS

        public Long getId() {
            return id;
        }

        public long getNextId() {
            return next_id;
        }

        public String getName() {
            return name;
        }

        public String getAchievements() {
            return achievements;
        }

        public AstartesCategory getCategory() {
            return category;
        }

        public Weapon getWeaponType() {
            return weaponType;
        }

        public Chapter getChapter() {
            return chapter;
        }

        public double getHealth() {
            return health;
        }

        public ZonedDateTime getCreationDate() {
            return creationDate;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        // END OF GETTERS

        // CONSTRUCTORS

        public SpaceMarine() {
            this.id = next_id++;
        }

        public SpaceMarine(String flag) {
            if ("update".equals(flag)) {
                
            } else {
                this.id = next_id++;
            }
            
        }

        public SpaceMarine(String name, Coordinates crdnt, double health, String achievements, AstartesCategory category, Weapon weapon, Chapter chapter) {
            this.id = next_id++;
            setName(name);
            this.coordinates = crdnt;
            this.creationDate = java.time.ZonedDateTime.now();
            this.health = health;
            this.achievements = achievements;
            this.category = category;
            this.weaponType = weapon;
            this.chapter = chapter;
        }

        public SpaceMarine(SpaceMarine other) {
            this.id = other.id;
            this.name = other.name;
            this.coordinates = other.coordinates;
            this.creationDate = other.creationDate;
            this.health = other.health;
            this.achievements = other.achievements;
            this.category = other.category;
            this.weaponType = other.weaponType;
            this.chapter = other.chapter;
        }

        // END OF CONSTRUCTORS

        @Override
        public int compareTo(SpaceMarine other) {
            return this.health.compareTo(other.health);
        }

    }

    static public class Coordinates {
        private Double x; //Поле не может быть null
        private float y;

        // SETTERS

        void setX(double x) {
            this.x = x;
        }

        void setY(float y) {
            this.y = y;
        }

        // END OF SETTERS

        // GETTERS

        double getX() {
            return x;
        }

        float getY() {
            return y;
        }

        // END OF GETTERS

        // CONSTRUCTORS

        public Coordinates() {
            x = 0D;
            y = 0F;
        }

        public Coordinates(Double x, float y) {
            this.x = x;
            this.y = y;
        }

        // END OF CONSTRUCTORS    
    }

    static public class Chapter {
        private String name; //Поле не может быть null, Строка не может быть пустой
        private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
        private String world; //Поле может быть null

        public Chapter(String name, int marinescnt, String wrld) {
            this.name = name;
            this.marinesCount = marinescnt;
            this.world = wrld;
        }

        // SETTERS

        public boolean setName(String name) {
            if (name.isEmpty()) {
                System.out.println("Name can not be empty!");
                return false;
            } 
            this.name = name;

            return true;
        }

        public void setMarinesCount(int cnt) {
            this.marinesCount = cnt;
        }

        public void setWorld(String world) {
            this.world = world;
        }

        // END OF SETTERS

        // GETTERS

        public String getName() {
            return name;
        }

        public String getWorld() {
            return world;
        }

        public int getMarinesCnt() {
            return marinesCount;
        }

        // END OF GETTERS
    }
    
    static public void printHelp() {
        System.out.println("✅info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("✅show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("✅add {element} : добавить новый элемент в коллекцию");
        System.out.println("✅update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("✅remove_by_id id : удалить элемент из коллекции по его id");        
        System.out.println("✅clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("✅exit : завершить программу (без сохранения в файл)");
        System.out.println("✅add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("✅add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("✅remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("✅average_of_health : вывести среднее значение поля health для всех элементов коллекции");
        System.out.println("✅count_less_than_chapter chapter : вывести количество элементов, значение поля chapter которых меньше заданного");
        System.out.println("✅print_field_ascending_achievements : вывести значения поля achievements всех элементов в порядке возрастания");
    }

    static void showInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 COLLECTION INFORMATION");
        System.out.println("=".repeat(50));
        System.out.println("📌 Collection type: TreeSet<SpaceMarine>");
        System.out.println("📅 Initialized: " + collection_init_date);
        System.out.println("📦 Elements count: " + collection.size());
        System.out.println("=".repeat(50));
    }

    static void printFieldAscendingAchievments() {
        int index = 1;
        for (SpaceMarine marine : collection) {
            System.out.println("\n🔹 MARINE #" + index++ + " (ID: " + marine.getId() + ")");
            System.out.println("   ├─ Achievements: " + (marine.getAchievements() != null ? marine.getAchievements() : "null"));
        }
    }

    static long countLessChptr() {
        SpaceMarine marine = new SpaceMarine();
        setMarineChapter(marine);

        long cnt = 0;

        for (SpaceMarine space_marine : collection) {
            if (space_marine.getChapter().getMarinesCnt() < marine.getChapter().getMarinesCnt()) {
                cnt++;
            }
        }

        return cnt;
    }

    static void addIfMin() {
        SpaceMarine marine = new SpaceMarine();

        fillMarine(marine, "creation");
        if (marine.getHealth() < minimum_collection_health) {
            collection.add(marine);
        } else {
            System.out.println("Your element is not less than least element in current collection!");
        }
    }

    static void addIfMax() {
        SpaceMarine marine = new SpaceMarine();

        fillMarine(marine, "creation");
        if (marine.getHealth() > maximum_collection_health) {
            collection.add(marine);
        } else {
            System.out.println("Your element is not bigger than max. element in current collection!");
        }
    }

    static Double getAvHealth() {
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
            String[] parts = user_input.split(" ", 2);
            String command = parts[0];
            String arg = parts.length > 1 ? parts[1] : null;
            
            switch (command) {
                case "exit" -> is_running = false;
                case "info" -> showInfo();
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
                case "add" -> {
                    SpaceMarine marine = new SpaceMarine();
                    fillMarine(marine, "creation");
                    collection.add(marine);

                    System.out.println("New marine added! His id is: " + marine.getId());
                }
                case "update" -> {
                    long id = Long.parseLong(arg);
                    updateById(id);
                }
                case "show" -> {
                    showCollection();
                }
                case "remove_by_id" -> {
                    long id = Long.parseLong(arg);
                    removeById(id);
                }
                case "print_field_ascending_achievements" -> {
                    printFieldAscendingAchievments();
                }
                case "add_if_min" -> {
                    addIfMin();
                }
                case "add_if_max" -> {
                    addIfMax();
                }
                case "remove_lower" -> {
                    removeLower();
                }
                case "count_less_than_chapter" -> {
                    System.out.println("Counter: " +  countLessChptr());
                }
                default -> { System.out.println("Wrong input! Type in 'help' for list of commands.");
                }
            }
        } 
    }

    static void removeLower() {
        SpaceMarine flag_marine = new SpaceMarine();

        fillMarine(flag_marine, "creation");

        collection.removeIf(marine -> marine.getHealth() < flag_marine.getHealth());
    }

    static void removeById(long id) {
        boolean removed = collection.removeIf(marine -> marine.getId() == id);

        if (removed) {
            System.out.println("Marine with id {" + id + "} was removed!");
        } else {
            System.out.println("Marine with id {" + id + "} was not removed!");
        }
    }

    static void showCollection() {
        if (collection.isEmpty()) {
            System.out.println("📭 Collection is empty!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(100));
        System.out.println("📋 COLLECTION CONTENTS (total: " + collection.size() + " marines)");
        System.out.println("=".repeat(100));
        
        int index = 1;
        for (SpaceMarine m : collection) {
            System.out.println("\n🔹 MARINE #" + index++ + " (ID: " + m.getId() + ")");
            System.out.println("   ├─ Name: " + m.getName());
            System.out.println("   ├─ Coordinates: (" + m.getCoordinates().getX() + ", " + m.getCoordinates().getY() + ")");
            System.out.println("   ├─ Created: " + m.getCreationDate());
            System.out.println("   ├─ Health: " + m.getHealth());
            System.out.println("   ├─ Achievements: " + (m.getAchievements() != null ? m.getAchievements() : "null"));
            System.out.println("   ├─ Category: " + (m.getCategory() != null ? m.getCategory() : "null"));
            System.out.println("   ├─ Weapon: " + (m.getWeaponType() != null ? m.getWeaponType() : "null"));
            System.out.println("   └─ Chapter: " + m.getChapter().getName() + 
                            " (count: " + m.getChapter().getMarinesCnt() + 
                            ", world: " + (m.getChapter().getWorld() != null ? m.getChapter().getWorld() : "null") + ")");
        }
        System.out.println("\n" + "=".repeat(100));
    }

    static SpaceMarine findById(long id) {
        for (SpaceMarine marine : collection) {
            if (marine.getId() == id) {
                return marine;
            }
        }
        return null;
    }

    static void updateById(long id) {
        SpaceMarine old_marine = findById(id);
        
        if (old_marine == null) {
            System.out.println("Marine with id " + id + " not found!");
            return;
        }
        
        SpaceMarine new_marine = new SpaceMarine(old_marine);
        
        new_marine.setId(old_marine.getId());
        new_marine.setCreationDate(old_marine.getCreationDate());
        
        fillMarine(new_marine, "update");
        
        collection.remove(old_marine);
        collection.add(new_marine);

        System.out.println("Marine updated succesfully!");
    }

    static boolean checkName(String name) {
        return !(name == null || " ".equals(name) || name.trim().isEmpty());
    }

    static void setCategory(SpaceMarine marine){
        boolean category_set = false;

        while (!category_set) {
            System.out.println("Enter marine's category (pick one & type in only the name): ");
            System.out.println("1. SCOUT");
            System.out.println("2. AGGRESSOR");
            System.out.println("3. TACTICAL");
            System.out.println("4. APOTHECARY");
            
            String user_input = global_scanner.nextLine().toUpperCase().trim();

            if (user_input.isEmpty()) {
                marine.setCategory(null);
                break;
            }
            
            switch (user_input) {
                case "SCOUT", "1" -> {
                    marine.setCategory(AstartesCategory.SCOUT);
                    category_set = true;
                }
                case "AGGRESSOR", "2" -> {
                    marine.setCategory(AstartesCategory.AGGRESSOR);
                    category_set = true;
                }
                case "TACTICAL", "3" -> {
                    marine.setCategory(AstartesCategory.TACTICAL);
                    category_set = true;
                }
                case "APOTHECARY", "4" -> {
                    marine.setCategory(AstartesCategory.APOTHECARY);
                    category_set = true;
                }
                default -> System.out.println("Wrong input! Try again!");
            }
        }
    }

    static void setMarineName(SpaceMarine marine) {
        while (true) {   
            System.out.println("Enter the marine name: ");
            String name = global_scanner.nextLine();
            
            if (checkName(name)) {
                marine.setName(name);
                break;
            } else {
                System.out.println("Name can't be blank. Please, enter again: ");
            }
        }
    }

    static void setMarineCoordinates(SpaceMarine marine) {
        while (true) { 
            Coordinates coordinates = new Coordinates();

            System.out.println("Enter x coordinate: ");
            int x = global_scanner.nextInt();
            global_scanner.nextLine();

            System.out.println("Enter y coordinate: ");
            int y = global_scanner.nextInt();
            global_scanner.nextLine();

            coordinates.setX(x);
            coordinates.setY(y);

            marine.setCoordinates(coordinates);

            break;
        }
    }

    static void setMarineHealth(SpaceMarine marine) {
        while (true) {
            System.out.println("Enter marine's health: ");
            String strhealth = global_scanner.nextLine().trim();

            if (strhealth.isEmpty()) {
                marine.setHealth(null);
                break;
            }

            Double health = Double.valueOf(strhealth);

            boolean ok = marine.setHealth(health);
            
            if (ok) {
                break;
            }
        }
    }

    static void setMarineAchievments(SpaceMarine marine) {
        while (true) { 
            System.out.println("Enter marine's achievments: ");

            String achievs = global_scanner.nextLine();

            if (achievs.isEmpty()) {
                marine.setAchievments(null);
                break;
            }

            marine.setAchievments(achievs);
            break;
        }
    }

    static void setMairneWeapon(SpaceMarine marine) {
        boolean weapon_set = false;
        while (!weapon_set) {
            System.out.println("Enter marine's weapon (pick one & type in only the name): ");
            System.out.println("1. MELTAGUN");
            System.out.println("2. BOLT_RIFLE");
            System.out.println("3. PLASMA_GUN");
            System.out.println("4. GRENADE_LAUNCHER");
            
            String user_input = global_scanner.nextLine().toUpperCase().trim();

            if (user_input.isEmpty()) {
                marine.setWeaponType(null);
                break;
            }
            
            switch (user_input) {
                case "MELTAGUN", "1"  -> {
                    marine.setWeaponType(Weapon.MELTAGUN);
                    weapon_set = true;
                }
                case "BOLT_RIFLE", "2" -> {
                    marine.setWeaponType(Weapon.BOLT_RIFLE);
                    weapon_set = true;
                }
                case "PLASMA_GUN", "3" -> {
                    marine.setWeaponType(Weapon.PLASMA_GUN);
                    weapon_set = true;
                }
                case "GRENADE_LAUNCHER", "4" -> {
                    marine.setWeaponType(Weapon.GRENADE_LAUNCHER);
                    weapon_set = true;
                }
                default -> System.out.println("Wrong input! Try again!");
            }
        }
    }

    static void setMarineChapter(SpaceMarine marine) {
        while (true) {
            int mrns_cnt = 0;
            boolean ok = false;
            System.out.println("Enter marine's chapter name: ");
            String chpt_name = global_scanner.nextLine();

            while (!ok) {
                System.out.println("Enter marine's chapter marinesCount: ");
                mrns_cnt = global_scanner.nextInt();

                if (mrns_cnt > 0 && mrns_cnt <= 1000) {
                    ok = true;
                } else {
                    System.out.println("Not a valid number. Please try again!");
                }
            }
            
            global_scanner.nextLine();
            System.out.println("Enter marine's chapter world: ");
            String wrld = global_scanner.nextLine(); 
            Chapter chapter = new Chapter(chpt_name, mrns_cnt, wrld);

            marine.setChapter(chapter);
            break;
        } 
    }

    static void setAllMC(SpaceMarine marine) {
        setMarineName(marine);

        setMarineCoordinates(marine);
            
        setMarineHealth(marine);

        setMarineAchievments(marine);

        setCategory(marine);

        setMairneWeapon(marine);

        setMarineChapter(marine);
    }

    static void fillMarine(SpaceMarine marine, String flag) {    
        if ("creation".equals(flag)) {
            System.out.println("*******| New marine creation |*******");
        } else if ("update".equals(flag)) {
            System.out.println("*******| Marine info  update |*******");
        }

        setAllMC(marine);

        if (marine.getHealth() > maximum_collection_health) {
            maximum_collection_health = marine.getHealth();
        }

        if (marine.getHealth() < minimum_collection_health) {
            minimum_collection_health = marine.getHealth();
        }
    }

    // File processor














    // End of file processor

    public static void main(String[] args) {
        collection = new TreeSet<>();
        global_scanner = new Scanner(System.in);
        collection_init_date = LocalDateTime.now();
        
        runProgramm(collection);
    }
}
