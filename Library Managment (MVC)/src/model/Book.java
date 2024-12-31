package model;

public class Book {
    private static Long nextId = 1L;
    private Long id;
    private String title;
    private String author;
    
    public enum Status {
        AVAILABLE, BORROWED
    }
    
    private Status status;
    
    // Constructor
    public Book(String title, String author) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.status = Status.AVAILABLE; // Default status when book is created
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    // Remove setId since id is now auto-generated
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
}
