package CAMs_App.controllers;

import java.util.Scanner;

import CAMs_App.service.AuthCampCompMemService;
import CAMs_App.service.AuthService;
import CAMs_App.service.AuthStudentService;
import CAMs_App.service.AuthStaffService;




public class AuthController {
    public static final Scanner sc = new Scanner(System.in);
    private static AuthService authService; // need to change to interface when the interface is done
    private AuthController(){};

    public static void startSession(){
        int choice;
        boolean authenticated = false;
        
        do{

            while (true){
                System.out.println("Login as: (enter 0 to exit)\n" + 
                                    "1. Student \n" + 
                                    "2. Staff \n"+
                                    "3. Camp Committee\n");
            
                String input = sc.nextLine();

                if (input.matches("[0-9]+")) {
                    choice = Integer.parseInt(input);

                    if (choice < 0 || choice > 3) {
                        System.out.println("Invalid input. Please enter a valid choice.\n");
                    }
                    
                    else {
                        break;
                    }
                    
                } else {
                    System.out.println("Invalid input. Please enter an integer.\n");
                }
            }
            
            switch (choice){
                case 0:
                    System.out.println("Ending program...");
                    return;

                case 1:
                    authService = new AuthStudentService();
                    break;

                case 2: 
                    authService = new AuthStaffService();
                    break;

                case 3:
                    authService = new AuthCampCompMemService();
                    break;
            }

            String userID, password;
            System.out.println("UserID: ");
            userID = sc.nextLine();

            System.out.println("Password ");
            password = sc.nextLine();

            authenticated = authService.login(userID, password);
            if (!authenticated){
                System.out.println("Credentials invalid! UserID & Password are case-sensitive.\n");
            }
        } while (!authenticated);
    }

    public static void endSession(){
        authService.logout();
        System.out.println("User logged out!");
    }

}
