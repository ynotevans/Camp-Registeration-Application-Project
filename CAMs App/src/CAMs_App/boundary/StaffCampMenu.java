package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.data.AuthData;
import CAMs_App.service.HelperService;
import CAMs_App.service.ColouredTextPrinter;
public class StaffCampMenu implements Menu {
    StaffController staffController = new StaffController();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.clearScreen();
        HelperService.printRoute("Staff Camp Menu ---> "+ AuthData.getCurrentCamp().getCampName());
        ColouredTextPrinter.printBlue("Available actions for this camp: ");
        ColouredTextPrinter.printBlue("(1) Edit camp");
        ColouredTextPrinter.printBlue("(2) Delete camps");
        ColouredTextPrinter.printBlue("(3) Toggle camp's visibility");
        ColouredTextPrinter.printBlue("(4) View enquiries for this camp");
        ColouredTextPrinter.printBlue("(5) Reply enquiries for this camp");
        ColouredTextPrinter.printBlue("(6) View suggestions for this camp");
        ColouredTextPrinter.printBlue("(7) Process suggestions for this camp");
        ColouredTextPrinter.printBlue("(8) Approve suggestions for this camp");
        ColouredTextPrinter.printBlue("(9) Generate committee performance report");
        ColouredTextPrinter.printBlue("(10) Generate Student report");
        ColouredTextPrinter.printBlue("(11) Generate Equiries Report"); 
        ColouredTextPrinter.printBlue("(12) Previous Page");
        ColouredTextPrinter.printBlue("(13) Logout\n");
        
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
                        HelperService.pressAnyKeyToContinue();
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
                        HelperService.wait(2);
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
                    
                    case 9:    //committee report
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Committee Performance Report");
                        staffController.generateCommitteeReport();
                        
                        this.viewApp();
                        break;
                    
                    case 10:    //student report
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Student Report");
                        staffController.generateStudentReport();
                        
                        this.viewApp();
                        break;
                    
                    case 11:    //enquiries report
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Enquiries Report");
                        staffController.generateEnquiriesReport();
                        
                        this.viewApp();
                        break;

                    case 12: //go to previous page
                        StaffMenu staffMenu = new StaffMenu();
                        staffMenu.viewApp();
                        break;
                                    
                    case 13:    //logout
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
