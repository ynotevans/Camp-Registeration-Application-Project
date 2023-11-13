package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.User;
import CAMs_App.service.HelperService;

public class StaffMenu implements Menu{
    User currentStaff = AuthData.getCurrentUser();

    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Welcome back"+ currentStaff.getUserID() + "...");
        System.out.println("(1) Login");
        System.out.println("(2) Change password");
        System.out.println("(3) Exit\n");
    }

    

    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Prompt to Login...");
                    
                    break;

                case 2:
                    System.out.println("Prompt to Change Password...");
                    break;
                
                case 3:
                    System.out.println("Prompt to Exit...");
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 3);
    
        sc.close();

    }
}
