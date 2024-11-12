/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sessions;

import DataModels.User;

/**
 *
 * @author Irzi Rhmtllh
 */
public class SessionManager {
    private static User currUser;
    
    public static void setCurrUser(User userData) {
        currUser = userData;
    };
    
    public static User getCurrUser() {
        return currUser;
    };
    
    public static void logout() {
        currUser = null;
    };
};