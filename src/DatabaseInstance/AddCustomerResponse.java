/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseInstance;

import java.util.ArrayList;

/**
 *
 * @author Irzi Rhmtllh
 */
public class AddCustomerResponse {
    public int status, id;
    public String message;
    
    public AddCustomerResponse(int status, String message, int id){
        this.status = status;
        this.message = message;
        this.id = id;
    }
    
};
