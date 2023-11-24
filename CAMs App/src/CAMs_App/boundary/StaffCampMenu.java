package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.data.AuthData;
import CAMs_App.service.HelperService;
import CAMs_App.entity.Student;
public class StaffCampMenu implements Menu {
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
        System.out.println("(7) Process suggestions for this camp");
        System.out.println("(8) Approve suggestions for this camp");
        System.out.println("(9) Previous Page");
        System.out.println("(10) Logout\n");
        
    }

    

    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            do{
                System.out.print("Enter ur action for this camp: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:    //edit camp
                        staffController.editCamp();
                        break;

                    case 2:    //delete camp
                        System.out.println("Enter camp name to be deleted: ");
                        String campName = sc.next();
                        staffController.deleteCamp(campName);
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

                    case 7:    //process suggestion
                        staffController.processSuggestions();
                        break;
                    
                    case 8:    //approve suggestion
                        staffController.approveSuggestion((Student)AuthData.getCurrentUser());
                        break;

                    case 9: //go to previous page
                        StaffMenu staffMenu = new StaffMenu();
                        staffMenu.viewApp();
                        break;
                                    
                    case 10:    //logout
                        System.out.println("Logging out...");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MainMenu menu = new MainMenu();
                        menu.viewApp();
                        break;
                    
                    default:
                        System.out.println("Invalid selection, please select again...");
                        break;
                }

            }while(choice != 10);
        
            sc.close();

        } catch (Exception e) {
            System.out.println("Invalid selection, please select again...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
            viewApp();
        }
    }
}
