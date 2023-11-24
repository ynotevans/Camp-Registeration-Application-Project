package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.data.AuthData;
import CAMs_App.service.HelperService;
public class StaffCampMenu implements Menu {
    StaffController staffController = new StaffController();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.clearScreen();
        HelperService.printRoute("Staff Camp Menu ---> "+ AuthData.getCurrentCamp().getCampName());
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
            do{
                System.out.print("Enter ur action for this camp: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:    //edit camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Edit Camp");
                        staffController.editCamp();
                        this.viewApp();
                        break;

                    case 2:    //delete camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Delete Camp");
                        System.out.println("Enter camp name to be deleted: ");
                        String campName = sc.next();
                        staffController.deleteCamp(campName);
                        this.viewApp();
                        break;
                    
                    case 3:    //toggle camp visibility
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Toggle Camp Visibility");
                        staffController.toggleVisibility();
                        this.viewApp();
                        break;
                    
                    case 4:    //view enquiries
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> View Enquiries");
                        staffController.viewEnquiries();
                        this.viewApp();
                        break;

                    case 5:    //reply enquiries
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Reply Enquiries");
                        staffController.replyEnquiries();
                        this.viewApp();
                        break;

                    case 6:    //view suggestion
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> View Suggestions");
                        staffController.viewSuggestions();
                        this.viewApp();
                        break;

                    case 7:    //process suggestion
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Process Suggestions");
                        staffController.processSuggestions();
                        this.viewApp();
                        break;
                    
                    case 8:    //approve suggestion
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Approve Suggestions");
                        staffController.approveSuggestion();
                        this.viewApp();
                        break;

                    case 9: //go to previous page
                        StaffMenu staffMenu = new StaffMenu();
                        staffMenu.viewApp();
                        break;
                                    
                    case 10:    //logout
                        System.out.println("Logging out...");
                        staffController.logout();
                        HelperService.wait(3);
                        MainMenu menu = new MainMenu();
                        menu.viewApp();
                        break;
                    
                    default:
                        System.out.println("Invalid selection, please select again...");
                        break;
                }

            }while(choice != 10);
        
            sc.close();

        }
}
