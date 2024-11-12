/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseInstance;

/**
 *
 * @author Irzi Rhmtllh
 */
public class DatabaseResultResponse {
    public int status;
    public String message;
    public DatabaseResultResponse(int status, String message){
        this.status = status;
        this.message = message;
    }
};
