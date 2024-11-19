/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contexts;

/**
 *
 * @author Irzi Rhmtllh
 */
public class CustomerContext {
    static private int idCustomer, dayReserved;
    static private String roomId, paymentId;
    
    public void setId(int id) {
        CustomerContext.idCustomer = id;
    };
    
    public void setRoomId(String id) {
        CustomerContext.roomId = id; 
    };
    
    public void setPaymentId(String uuid) {
        CustomerContext.paymentId = uuid;
    };
    
    public void setDayReserved(int day) {
        CustomerContext.dayReserved = day;
    };
    
    public int getId() {
        return idCustomer;
    };
    
    public String getRoomId() {
        return roomId;
    };
    
    public String getPaymentId() {
        return paymentId;
    };
    
    public int getDayReserved() {
        return dayReserved;
    };
}
