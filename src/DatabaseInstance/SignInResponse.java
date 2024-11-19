/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseInstance;

/**
 *
 * @author Irzi Rhmtllh
 */
public class SignInResponse {
    public String message, role, status;

    public SignInResponse(String message, String role, String status) {
        this.message = message;
        this.role = role;
        this.status = status;
    };
}
