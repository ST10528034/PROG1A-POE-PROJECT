/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Login extends Registration {
    
    public boolean loginUser(String username, String password){ //this is the method for number 2.login to the account using the same username and password.
        return this.userName.equals(username) &&
                this.passWord.equals(password);
    }
    
    public String returnLoginStatus(String username, String password, String kyle, String smith){
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
    
         if (loginUser(username, password)){
             
             return "Welcome " + firstName + " " + lastName + " It is great to see you again. ";
}else{
    return "Username or password incorrect, please try again.";
} 
    
}
}
