package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.CampComController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.CampManagementService;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;

public class CampComMenu implements Menu{
    Student currentStudent = (Student)AuthData.getCurrentUser();
    CampComController campComController = new CampComController();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Camp committee Menu ---> "+ AuthData.getCurrentCamp().getCampName());
        ColouredTextPrinter.printBlue("Welcome back "+ currentStudent.getName() + "!!!");
        ColouredTextPrinter.printBlue("Available actions for you: ");
        ColouredTextPrinter.printBlue("(1) View camp details");
        ColouredTextPrinter.printBlue("(2) View enquiries");
        ColouredTextPrinter.printBlue("(3) Reply enquiries");
        ColouredTextPrinter.printBlue("(4) Create suggestion");
        ColouredTextPrinter.printBlue("(5) View your suggestions");
        ColouredTextPrinter.printBlue("(6) Delete your suggestions");
        ColouredTextPrinter.printBlue("(7) Generate enquiries report");
        ColouredTextPrinter.printBlue("(8) Generate attendence report");
        ColouredTextPrinter.printBlue("(9) Switch to student mode");
        ColouredTextPrinter.printBlue("(10) Logout\n");
        ColouredTextPrinter.printBlue("(11) Exit");
    }

    

    public void viewApp(){
        
        Scanner sc = new Scanner(System.in);
        int choice;
        
            do{
                this.printMenu();
                System.out.print("Enter ur selection: ");
                choice = HelperService.readInt();

                switch (choice) {
                    case 1:    //view camp details
                        campComController.viewCampDetails();
                        HelperService.pressAnyKeyToContinue();
                        break;

                    case 2:    //view  enquiries
                        campComController.viewAllEnquiries();
                        HelperService.pressAnyKeyToContinue();
                        break;
                
                    case 3:    //reply enquiries
                        campComController.replyEnquiry();  
                        HelperService.pressAnyKeyToContinue();
                        break;
                    
                    case 4:    //create suggestion
                        campComController.createSuggestion();
                        HelperService.pressAnyKeyToContinue();
                        break;
                    
                    case 5:    //view suggestions
                        campComController.viewSuggestion();
                        HelperService.pressAnyKeyToContinue();
                        break;
                    
                    case 6: //delete suggestions
                        campComController.deleteSuggestion();
                        HelperService.pressAnyKeyToContinue();
                        break;

                    case 7:    //generate enquiries report
                        CampManagementService.enquiriesReport();
                        HelperService.pressAnyKeyToContinue();
                        break;
                    
                    case 8:    //generate attendance report
                        System.out.println("Press 1 to faculty by faculty");
                        int filter = HelperService.readInt();
                        if(filter == 1){
                            System.out.println("Choose the faculty");
                            String faculty = sc.nextLine();
                            CampManagementService.generateStudentListReport(faculty);
                        }
                        else{
                            CampManagementService.generateStudentListReport();
                        }
                        HelperService.pressAnyKeyToContinue();
                        break;
                    
                    case 9:    //switch mode
                        campComController.switchMode(2);
                        break;

                    case 10:    //logout
                        campComController.logout();
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.viewApp();
                        break;
                    
                    default:
                        System.out.println("Invalid selection, please select again...");
                        break;
                }

            }while(choice != 11);
        
            sc.close();

        }
}
