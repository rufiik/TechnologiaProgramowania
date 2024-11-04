package com.biblioteka;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String name;
    private List<Copy> borrowedCopies;

    public Reader(String name) {
        this.name = name;
        this.borrowedCopies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Copy> getBorrowedCopies() {
        return borrowedCopies;
    }

    public void borrowCopy(Copy copy) {
        copy.borrow();
        borrowedCopies.add(copy);
    }

    public void returnCopy(Copy copy) {
        copy.returnCopy();
        borrowedCopies.remove(copy);
    }
}
