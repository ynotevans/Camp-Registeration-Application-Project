package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.User;
import CAMs_App.service.HelperService;

public class StaffMenu implements Menu{
    User currentStaff = AuthData.getCurrentUser();
    StaffController staffController = new StaffController();

    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Welcome back "+ currentStaff.getUserID() + "!!!");
        System.out.println("Available actions for you: ");
        System.out.println("(1) Create new camp");
        System.out.println("(2) View all camps");
        System.out.println("(3) View camp(s) created by you");
        System.out.println("(4) Logout\n");
    }

    

    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:    //create camp
                    staffController.createCamp();
                    break;

                case 2:    //view all camp
                    staffController.viewAllCamp();
                    break;
                
                case 3:    //view staff created camp
                    staffController.viewCreatedCamp(currentStaff.getUserID());

                    CampMenu campMenu = new CampMenu();
                    campMenu.viewApp();
                    break;
                
                case 4:    //logout
                    staffController.logout();
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 4);
    
        sc.close();

    }
}
