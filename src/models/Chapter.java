package cse_labwork5.src.models;

public class Chapter {
    private String name;
    private int marinesCount;
    private String world;

    public Chapter(String name, int marinescnt, String wrld) {
        this.name = name;
        this.marinesCount = marinescnt;
        this.world = wrld;
    }

    public Chapter() {

    }

    public void setName(String name) {
        if (name.isEmpty()) return;
        this.name = name;
    }

    public void setMarinesCount(int cnt) { 
        this.marinesCount = cnt; 
    }

    public void setWorld(String world) { 
        this.world = world; 
    }

    public String getName() { 
        return name; 
    }

    public String getWorld() { 
        return world; 
    }

    public int getMarinesCnt() { 
        return marinesCount; 
    }
}