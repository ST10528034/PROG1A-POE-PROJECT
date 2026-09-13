/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

/**
 *
 * @author Student
 */
public class Registration { //start of class
    
    String userName;
    String passWord;
    String cellPhone;
    
    public boolean checkUserName(String username){ //start of username method
        if(username.contains("_") && username.length() <= 5){
            System.out.println("Username successully captured.");
            return true;
        }else{
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            return false;       
        }
    } //end of username method
    
    public boolean checkPasswordComplexity(String password){ //start of my password method
        if(password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[^a-zA-Z0-9].*")){
        System.out.println("Password successfully captured.");
        return true;
        }else{
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
            }
    }//end of password method
    
    public boolean checkCellPhoneNumber(String cellphone){ //start of the method for checking cellphone number length is correct
        if(cellphone.matches(".*^0[678][0-9]{8}$") || cellphone.matches(".*^\\+27[678][0-9]{8}$")){
            System.out.println("Cell phone number successfully captured.");
            return true;
        }else{
            System.out.println("Cell phone number INCORRECTLY formatted or does not contain international code.");
            return false;
        }
        
    }//end of cellphone method
    
    public String registerUser(String username, 
            String password, 
            String cellphone){
        
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        boolean validCellphone = checkCellPhoneNumber(cellphone);
        
        if(validUsername && 
                validPassword &&
                validCellphone){
            this.userName = username;
            this.passWord = password;
            this.cellPhone = cellphone;
            
            return "REGISTRATION WAS A SUCCESS!!";
        }else{
            return "REGISTRATION WAS UNSUCCESSFUL!!";
        }
        
    }
   
    public boolean registerStatus(String username, String password , String cellphone){
        
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        boolean validCellphone = checkCellPhoneNumber(cellphone);
        
        return validUsername &&
                validPassword &&
                validCellphone;
                
    }
    
    public boolean loginUser(String username, String password){ //this is the method for number 2.login to the account using the same username and password.
        return this.userName.equals(username) &&
                this.passWord.equals(password);
    }
    
    public String returnLoginStatus(String username, String password, String kyle, String smith){
    
         if (loginUser(username, password)){
             String firstName = null;
             String lastName = null;
             return "Welcome " + firstName + " " + lastName + " It is great to see you again. ";
}else{
    return "Username or password incorrect, please try again.";
} 
    }
    
} //end of class
