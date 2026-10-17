/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

import javax.swing.J0ptionPane;

/**
 *
 * @author Student
 */
public class Login {

    private String username;
    private String password;

    public void register() {
        username = JOptionPane.showInputDialog("Register - enter a username:");
        password = JOptionPane.showInputDialog("Register - enter a password:");
        JOptionPane.showMessageDialog(null, "Registration successful.");
    }

    public boolean login() {
        String u = JOptionPane.showInputDialog("Login - username:");
        String p = JOptionPane.showInputDialog("Login - password:");
        boolean ok = u != null && p != null && u.equals(username) && p.equals(password);
        JOptionPane.showMessageDialog(null,
                ok ? "Login successful." : "Username or password incorrect.");
        return ok;
    }
}