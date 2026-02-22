package cse_labwork5.src.models;

import java.time.ZonedDateTime;


public class SpaceMarine implements Comparable<SpaceMarine> {
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
        this.id = other.getId();
        this.name = other.getName();
        this.coordinates = other.getCoordinates();
        this.creationDate = other.getCreationDate();
        this.health = other.getHealth();
        this.achievements = other.getAchievements();
        this.category = other.getCategory();
        this.weaponType = other.getWeaponType();
        this.chapter = other.getChapter();
    }

    // END OF CONSTRUCTORS

    @Override
    public int compareTo(SpaceMarine other) {
        return this.health.compareTo(other.getHealth());
    }
}