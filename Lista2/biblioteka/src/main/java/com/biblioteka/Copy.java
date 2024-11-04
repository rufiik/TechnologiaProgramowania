package com.biblioteka;

public class Copy {
    private static int idCounter = 1;
    private int id;
    private boolean borrowed;

    public Copy() {
        this.id = idCounter++;
        this.borrowed = false;
    }

    public int getId() {
        return id;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void borrow() {
        this.borrowed = true;
    }

    public void returnCopy() {
        this.borrowed = false;
    }
}
