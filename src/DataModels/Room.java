/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataModels;

/**
 *
 * @author Irzi Rhmtllh
 */
public class Room {
    public String roomId, name, type;
    public int price;
    public Room(String roomId, String name, String type, int price ) {
        this.roomId = roomId;
        this.name = name;
        this.type = type;
        this.price = price;
    };
};
