/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataModels;

/**
 *
 * @author Irzi Rhmtllh
 */
public class User {
    private int userId;
    private String username, email, name, role, status;
    
    public User(String username, String email, String name, String role, String status, int userId){
       this.username = username;
       this.email = email;
       this.name = name;
       this.role = role;
       this.status = status;
       this.userId = userId;
    };
    
    public void setId(int userId ) {
        this.userId = userId;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getStatus() {
        return email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getUsername() {
        return username;
    }

    public int getId() {
        return userId;
    }
    
}
