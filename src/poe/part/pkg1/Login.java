/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

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
    
         if (loginUser(username, password)){
             String firstName = "Kutlwi";
             String lastName = "Malope";
             return "Welcome " + firstName + " " + lastName + " It is great to see you again. ";
}else{
    return "Username or password incorrect, please try again.";
} 
    
}
}
