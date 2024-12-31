package model;

public class User {
    private static Long nextId = 1L;
    private Long id;
    private String username;
    private String password;
    
    public enum Role {
        ADMIN, USER
    }
    
    private Role role;
    
    // Constructor
    public User(String username, String password, Role role) {
        this.id = nextId++;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}
