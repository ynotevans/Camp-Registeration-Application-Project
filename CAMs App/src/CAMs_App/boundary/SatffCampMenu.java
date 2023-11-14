package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.service.HelperService;

public class SatffCampMenu implements Menu {
    StaffController staffController = new StaffController();

    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Available actions for this camp: ");
        System.out.println("(1) Edit camp");
        System.out.println("(2) Delete camps");
        System.out.println("(3) Toggle camp's visibility");
        System.out.println("(4) View enquiries for this camp");
        System.out.println("(5) Reply enquiries for this camp");
        System.out.println("(6) View suggestions for this camp");
        System.out.println("(7) Approve suggestions to this camp");
        System.out.println("(8) Logout\n");
    }

    

    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:    //edit camp
                    staffController.editCamp();
                    break;

                case 2:    //delete camp
                    staffController.deleteCamp();
                    break;
                
                case 3:    //toggle camp visibility
                    staffController.toggleVisibility();
                    break;
                
                case 4:    //view enquiries
                    staffController.viewEnquiries();
                    break;

                case 5:    //reply enquiries
                    staffController.replyEnquiries();
                    break;

                case 6:    //view suggestion
                    staffController.viewSuggestions();
                    break;

                case 7:    //approve suggestion
                    staffController.processSuggestions();
                    break;
                
                case 8:    //logout
                    staffController.logout();
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 8);
    
        sc.close();

    }
}
