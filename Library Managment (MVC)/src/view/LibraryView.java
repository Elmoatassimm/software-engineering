package view;

import java.util.Scanner;
import controller.LibraryController;
import model.User;

public class LibraryView {
    private LibraryController controller;
    private Scanner scanner;
    
    public LibraryView(LibraryController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        while (true) {
            if (controller.getCurrentUser() == null) {
                showLoginMenu();
            } else if (controller.getCurrentUser().getRole() == User.Role.ADMIN) {
                showAdminMenu();
            } else {
                showUserMenu();
            }
        }
    }
    
    private void showLoginMenu() {
        System.out.println("\n=== Library System - Login ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                handleLogin();
                break;
            case "2":
                handleRegistration();
                break;
            case "3":
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void showAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Logout");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                handleAddBook();
                break;
            case "2":
                controller.listBooks();
                break;
            case "3":
            showLoginMenu();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void showUserMenu() {
        System.out.println("\n=== User Menu ===");
        System.out.println("1. List All Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Logout");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                controller.listBooks();
                break;
            case "2":
                handleBorrowBook();
                break;
            case "3":
                handleReturnBook();
                break;
            case "4":
                controller = new LibraryController();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void handleLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (!controller.login(username, password)) {
            System.out.println("Invalid credentials!");
        }
    }
    
    private void handleRegistration() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (ADMIN/USER): ");
        String role = scanner.nextLine();
        
        try {
            controller.registerUser(username, password, role);
            System.out.println("Registration successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role! Please use ADMIN or USER");
        }
    }
    
    private void handleAddBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        
        controller.addBook(title, author);
        System.out.println("Book added successfully!");
    }
    
    private void handleBorrowBook() {
        System.out.print("Enter book ID to borrow: ");
        Long id = Long.parseLong(scanner.nextLine());
        controller.borrowBook(id);
        System.out.println("Book borrowed successfully!");
    }
    
    private void handleReturnBook() {
        System.out.print("Enter book ID to return: ");
        Long id = Long.parseLong(scanner.nextLine());
        controller.returnBook(id);
        System.out.println("Book returned successfully!");
    }
}
