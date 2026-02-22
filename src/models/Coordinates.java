package cse_labwork5.src.models;

public class Coordinates {
    private Double x; //Поле не может быть null
    private float y;

    // SETTERS

    public void setX(double x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    // END OF SETTERS

    // GETTERS

    public double getX() {
        return x;
    }

    public float getY() {
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