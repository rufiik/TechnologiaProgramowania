package com.biblioteka;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public void addBook(String title) {
        books.add(new Book(title));
        System.out.println("Dodano książkę: " + title);
    }

    public void addCopy(String title) {
        Optional<Book> book = books.stream().filter(b -> b.getTitle().equals(title)).findFirst();
        if (book.isPresent()) {
            book.get().addCopy(new Copy());
            System.out.println("Dodano egzemplarz dla książki: " + title);
        } else {
            System.out.println("Nie znaleziono książki o tytule: " + title);
        }
    }

    public void addReader(String name) {
        readers.add(new Reader(name));
        System.out.println("Dodano czytelnika: " + name);
    }

    public void borrowBook(String title, String readerName) {
        Optional<Book> book = books.stream().filter(b -> b.getTitle().equals(title)).findFirst();
        Optional<Reader> reader = readers.stream().filter(r -> r.getName().equals(readerName)).findFirst();

        if (book.isPresent() && reader.isPresent()) {
            Copy copy = book.get().getAvailableCopy();
            if (copy != null) {
                reader.get().borrowCopy(copy);
                System.out.println(readerName + " wypożyczył egzemplarz książki: " + title);
            } else {
                System.out.println("Brak dostępnych egzemplarzy książki: " + title);
            }
        } else {
            System.out.println("Nie znaleziono książki lub czytelnika.");
        }
    }

    public void displayBooks() {
        System.out.println("Książki w bibliotece:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " (Egzemplarzy: " + book.getCopies().size() + ")");
        }
    }

    public void displayReaders() {
        System.out.println("Czytelnicy w systemie:");
        for (Reader reader : readers) {
            System.out.println("- " + reader.getName() + " (Wypożyczonych książek: " + reader.getBorrowedCopies().size() + ")");
        }
    }
}
