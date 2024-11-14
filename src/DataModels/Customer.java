/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataModels;

/**
 *
 * @author Irzi Rhmtllh
 */
public class Customer {
    public int id;
    public String name, employeeName, roomId, roomType, roomName, checkInDate, checkOutDate;
    
    public Customer(int id, String name, String employeeName, String roomId, String roomName, String roomType, String checkInDate, String checkOutDate) {   
        this.id = id;
        this.name = name;
        this.employeeName = employeeName;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomName = roomName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    };
    
}
