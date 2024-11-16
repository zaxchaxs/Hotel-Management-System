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
    double priceTotal;
    public String name, employeeName, roomId, roomType, roomName, checkInDate, checkOutDate, status, phoneNumber, email;
    
    
    // overload fulldata
    public Customer(int id, String name, String email, String phoneNum, String employeeName, String roomId, String roomName, String roomType, String checkInDate, String checkOutDate, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNum;
        this.employeeName = employeeName;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomName = roomName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        
    };
    
    
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
    
    // overload utk param statuss
    public Customer(int id, String name, String employeeName, String roomId, String roomName, String roomType, String checkInDate, String checkOutDate, String status) {   
        this.id = id;
        this.name = name;
        this.employeeName = employeeName;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomName = roomName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
    };
    
}
