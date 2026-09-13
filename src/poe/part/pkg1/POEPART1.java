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
        
        //username input and checker
        System.out.println("Enter your username: ");
        String username = scan.nextLine();//this nextLine is for reading a string
        
        //password input and checker
        System.out.println("Enter your password: ");
        String password = scan.nextLine();
        
         //cellphone input and checker
        System.out.println("Enter your cellphone number: ");
        String cellphone = scan.nextLine();
        
        System.out.println(register.registerUser(
                username, 
                password, 
                cellphone));
        
        if(register.registerStatus(username, password, cellphone)){
             
            System.out.println("\n===== Login =====");
            
            System.out.println("Enter username: ");
            String loginUsername = scan.nextLine();
            
            System.out.println("Enter password; ");
            String loginPassword = scan.nextLine();
            
            String message = register.returnLoginStatus(
                             loginUsername,
                             loginPassword,
                             "Kutlwi",
                             "Malope");
               
            System.out.println(message);
        }        
        scan.close();
        }
        
    }
    

