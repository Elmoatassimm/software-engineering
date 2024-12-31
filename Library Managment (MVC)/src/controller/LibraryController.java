package controller;

import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.User;

public class LibraryController {
    private List<Book> books;
    private List<User> users;
    private User currentUser;
    
    public LibraryController() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.currentUser = null;
    }
    
    public void registerUser(String username, String password, String role) {
        User.Role userRole = User.Role.valueOf(role.toUpperCase());
        User newUser = new User(username, password, userRole);
        users.add(newUser);
    }
    
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    
    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }
    
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        
        for (Book book : books) {
            System.out.println("ID: " + book.getId().toString() + 
                             ", Title: " + book.getTitle() + 
                             ", Author: " + book.getAuthor() + 
                             ", Status: " + book.getStatus());
        }
    }
    
    public void borrowBook(Long bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId) && book.getStatus() == Book.Status.AVAILABLE) {
                book.setStatus(Book.Status.BORROWED);
                return;
            }
        }
    }
    
    public void returnBook(Long bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId) && book.getStatus() == Book.Status.BORROWED) {
                book.setStatus(Book.Status.AVAILABLE);
                return;
            }
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
}
