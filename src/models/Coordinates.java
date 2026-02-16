package cse_labwork5.src.models;

public class Coordinates {
    private Double x;
    private float y;

    public Coordinates() {
        x = 0D;
        y = 0F;
    }

    public Coordinates(Double x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) { 
        this.x = x; 
    }

    public void setY(float y) {
        this.y = y; 
    }

    public double getX() { 
        return x; 
    }

    public float getY() { 
        return y; 
    }
}