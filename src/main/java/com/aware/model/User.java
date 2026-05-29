package com.aware.model;

// Represents a user in the system
public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String role; // "user" or "admin"

    public User() {}

    public User(int id, String username, String email, String password, String role) {
        this.id       = id;
        this.username = username;
        this.email    = email;
        this.password = password;
        this.role     = role;
    }

    // Getters and Setters
    public int    getId()                   { return id; }
    public void   setId(int id)             { this.id = id; }

    public String getUsername()             { return username; }
    public void   setUsername(String u)     { this.username = u; }

    public String getEmail()                { return email; }
    public void   setEmail(String e)        { this.email = e; }

    public String getPassword()             { return password; }
    public void   setPassword(String p)     { this.password = p; }

    public String getRole()                 { return role; }
    public void   setRole(String r)         { this.role = r; }

    public boolean isAdmin()                { return "admin".equalsIgnoreCase(role); }
}
