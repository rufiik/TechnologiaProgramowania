package com.biblioteka;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Copy> copies;

    public Book(String title) {
        this.title = title;
        this.copies = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void addCopy(Copy copy) {
        this.copies.add(copy);
    }

    public Copy getAvailableCopy() {
        for (Copy copy : copies) {
            if (!copy.isBorrowed()) {
                return copy;
            }
        }
        return null;
    }
}
