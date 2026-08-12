/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe.part.pkg1;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class POEPART1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Registration register = new Registration();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\n==== Register ====");
        
        System.out.println("please enter the username:");
        String name = scan.nextLine();//this nextLine is for reading a string
        
        //password input and checker
        System.out.println("Enter password");
        String password = scan.nextLine();
        
        System.out.println("Enter cellphone");
        String cellphone = scan.nextLine();
        
        System.out.println(register.registerUser(name, 
                password, cellphone));
        
        if(register.registerStatus(name, password, cellphone)){
        return;
        }
        
        System.out.println("\n==== Register ====");
    }
    
}
