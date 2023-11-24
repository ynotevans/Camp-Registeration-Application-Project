package CAMs_App.controllers;
import java.util.Scanner;

import CAMs_App.service.AuthStaffService;
import CAMs_App.service.AuthStudentService;
import CAMs_App.service.AuthService;
import CAMs_App.service.UserService;

public class UserController {
    public static final Scanner sc = new Scanner(System.in);
    AuthService authService;
    public UserController(){}

    public void changePassword(){
        String oldPassword, newPassword;
        boolean success = false;
        System.out.println("Changing Password...");

        do {
            System.out.println("Enter your old password (Enter X to quit): ");
            oldPassword = sc.next();
            if (oldPassword.equalsIgnoreCase("X")) {
                System.out.println("Exiting change password... ");
            }

            System.out.println("Enter your new password: ");
            newPassword = sc.next();

            if (newPassword.equals(oldPassword)){
                System.out.println("New password cannot be the same as old password.");
                continue;
            }

            success = UserService.changePassword(oldPassword,newPassword);

            if (!success) {
                System.out.println("Old password does not match.");
            }

        } while (!success);

        System.out.println("Password successfully changed.");
    }

    public boolean login(boolean isStaff){
        int attempt=0;
        if(isStaff){
           authService = new AuthStaffService();
        }
        else{
           authService = new AuthStudentService();
        }

        System.out.print("User ID: ");
        String userID = sc.next();
        System.out.print("Password: ");
        String password = sc.next();
        boolean authenticated = authService.login(userID, password);;

        while(authenticated != true){  
            attempt++;        
            System.out.println("Attempt " + attempt + ": Wrong UserID or Password, please enter again...");
            System.out.print("User ID: ");
            userID = sc.next();
            System.out.print("Password: ");
            password = sc.next();
            authenticated = authService.login(userID, password);
            if (attempt == 4) {
                System.out.println("You have reached the maximum number of attempts.");
                return false;
            }
        }

        System.out.println("Login success"); 
        return true;      
    }

    public void logout(){
        AuthService.logout();
        System.out.println("Successfully logout");
     }

}