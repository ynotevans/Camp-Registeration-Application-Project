package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.service.HelperService;

public class CampMenu implements Menu {
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
                    System.out.println("Enter current camp name: \n");
                    String curCampName = sc.nextLine();
                    staffController.editCamp(curCampName);
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
                    System.out.println("Enter the suggestion number you want to process: \n");
                    int suggNumber = sc.nextInt();
                    staffController.processSuggestions(suggNumber);
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
