package com.biblioteka;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Dodaj egzemplarz książki");
            System.out.println("3. Dodaj czytelnika");
            System.out.println("4. Wypożycz książkę");
            System.out.println("5. Wyświetl książki");
            System.out.println("6. Wyświetl czytelników");
            System.out.println("7. Wyjdź");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Pobierz nową linię po wczytaniu liczby

            switch (choice) {
                case 1 -> {
                    System.out.print("Podaj tytuł książki: ");
                    String title = scanner.nextLine();
                    library.addBook(title);
                }
                case 2 -> {
                    System.out.print("Podaj tytuł książki: ");
                    String title = scanner.nextLine();
                    library.addCopy(title);
                }
                case 3 -> {
                    System.out.print("Podaj imię czytelnika: ");
                    String name = scanner.nextLine();
                    library.addReader(name);
                }
                case 4 -> {
                    System.out.print("Podaj tytuł książki: ");
                    String title = scanner.nextLine();
                    System.out.print("Podaj imię czytelnika: ");
                    String readerName = scanner.nextLine();
                    library.borrowBook(title, readerName);
                }
                case 5 -> library.displayBooks();
                case 6 -> library.displayReaders();
                case 7 -> {
                    System.out.println("Do widzenia!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór.");
            }
        }
    }
    

}
