package com.biblioteka;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Klasa Library reprezentuje bibliotekę.
 * Biblioteka posiada listę książek oraz listę czytelników.
 * Umożliwia dodawanie książek, egzemplarzy, czytelników,
 * wypożyczanie książek oraz wyświetlanie listy książek i czytelników.
 * Zastosowane wzorce GRASP:
 * - Information Expert: Klasa Library jest odpowiedzialna za zarządzanie
 *  książkami i czytelnikami, ponieważ posiada odpowiednie informacje.
 * - Low Coupling: Klasa Library ma niskie sprzężenie z innymi klasami,
 *  ponieważ używa interfejsów publicznych klas Book i Reader.
 * - High Cohesion: Klasa Library jest odpowiedzialna tylko za
 *  zarządzanie książkami i czytelnikami, co zapewnia wysoką spójność.
 */
public class Library {
    /** database - baza danych. */
    private Database database;
/**
 * Konstruktor tworzy bibliotekę z podaną bazą danych.
 * @param pdatabase
 */
    public Library(final Database pdatabase) {
        this.database = pdatabase;
    }

    /**
     * Metoda addBook dodaje nową książkę do biblioteki.
     * @param title tytuł książki
     * @param numberOfCopies liczba egzemplarzy książki
     */
    public void addBook(final String title, final int numberOfCopies) {
        Optional<Book> existingBook = database.loadBooks().stream().filter(b -> b.getTitle().equals(title)).findFirst();
        if (existingBook.isPresent()) {
            System.out.println("Książka o tytule " + title + " już istnieje.");
        } else {
            Book newBook = new Book(title);
            for (int i = 0; i < numberOfCopies; i++) {
                newBook.addCopy(new Copy());
            }
            database.saveBook(newBook);
            System.out.println("Dodano książkę: " + title + " z " + numberOfCopies + " egzemplarzami.");
        }
    }

    /**
     * Metoda addCopy dodaje nowe egzemplarze do istniejącej książki.
     * @param title tytuł książki
     * @param numberOfCopies liczba egzemplarzy do dodania
     * @param scanner skaner do pobierania danych od użytkownika
     */
    public final void addCopy(final String title, final int numberOfCopies, final Scanner scanner) {
        Optional<Book> book = database.loadBooks().stream().filter(b -> b.getTitle().equals(title)).findFirst();
        if (book.isPresent()) {
            for (int i = 0; i < numberOfCopies; i++) {
                book.get().addCopy(new Copy());
            }
            database.saveBook(book.get());
            System.out.println("Dodano " + numberOfCopies + " egzemplarzy dla książki: " + title);
        } else {
            System.out.println("Nie znaleziono książki o tytule: " + title);
            System.out.print("Czy chcesz dodać tę książkę do biblioteki? (tak/nie): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("tak")) {
                addBook(title, numberOfCopies);
                System.out.println("Dodano książkę: " + title + " z " + numberOfCopies + " egzemplarzami.");
            } else {
                System.out.println("Nie dodano książki do biblioteki.");
            }
        }
    }

    /**
     * Metoda addReader dodaje nowego czytelnika do biblioteki.
     * @param name imię czytelnika
     */
    public void addReader(final String name) {
        Reader reader = new Reader(name);
        database.saveReader(reader);
        System.out.println("Dodano czytelnika: " + name);
    }

    /**
     * Metoda borrowBook wypożycza książkę czytelnikowi.
     * @param title tytuł książki
     * @param readerName imię czytelnika
     */
    public void borrowBook(final String title, final String readerName) {
        Optional<Book> book = database.loadBooks().stream().filter(b -> b.getTitle().equals(title)).findFirst();
        Optional<Reader> reader = database.loadReaders().stream().filter(r -> r.getName().equals(readerName)).findFirst();
        if (book.isPresent() && reader.isPresent()) {
            Optional<Copy> copy = book.get().getCopies().stream().filter(c -> !c.isBorrowed()).findFirst();
            if (copy.isPresent()) {
                copy.get().setBorrowed(true);
                database.saveBook(book.get());
                System.out.println("Książka " + title + " została wypożyczona czytelnikowi " + readerName);
            } else {
                System.out.println("Brak dostępnych egzemplarzy książki " + title);
            }
        } else {
            System.out.println("Nie znaleziono książki lub czytelnika.");
        }
    }

    /**
     * Metoda displayBooks wyświetla listę książek w bibliotece.
     */
    public void displayBooks() {
        System.out.println("Lista książek w bibliotece:");
        for (Book book : database.loadBooks()) {
            System.out.println("Tytuł: " + book.getTitle() + ", liczba egzemplarzy: " + book.getCopies().size());
        }
    }

    /**
     * Metoda displayReaders wyświetla listę czytelników w bibliotece.
     */
    public void displayReaders() {
        System.out.println("Lista czytelników w bibliotece:");
        for (Reader reader : database.loadReaders()) {
            System.out.println("Imię: " + reader.getName());
        }
    }

    /**
     * Zwraca listę książek w bibliotece.
     * @return lista książek
     */
    public List<Book> getBooks() {
        return database.loadBooks();
    }

    /**
     * Zwraca listę czytelników w bibliotece.
     * @return lista czytelników
     */
    public List<Reader> getReaders() {
        return database.loadReaders();
    }
}
