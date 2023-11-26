package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.CampComController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.CampManagementService;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;
/**
 * The {@link CampComMenu} implements the generic menu, {@link Menu}. 
 * It provides methods to print the main menu and viewing the application.
 * The class uses {@link campComController} to manage camp committee-related activities and 
 * also uses {@link CampManagementService} in generating reports.
 */
public class CampComMenu implements Menu{
    /** The currently logged-in Student user. */
    Student currentStudent = (Student)AuthData.getCurrentUser();
    /** The controller for camp committee-related activities */
    CampComController campComController = new CampComController();
    /**
     * The printMenu() method displays the camp menu options available for the camp committee user.
     * It prints camp committee menu options such as view camp details, view enquiries, reply enquiries, create suggestions, view own suggestions, delete own suggestions, generate enquiries report, generate attendence report, 
     * switch to user mode and logout.
     */
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
    }

    
    /**
     * The viewApp() method allows camp committee user to perform various camp related actions for the selected camp such as 
     * <p><ul>
     * <li>View camp details : Camp Committee user view camp details thorugh {@link CampComController#viewCampDetails()}.
     * <li>View enquiries : Camp Committee user view the camp's enquiries through {@link CampComController#viewAllEnquiries()}.
     * <li>Reply enquiries : Camp Committee user reply the camp's enquiries through {@link CampComController#replyEnquiry()}.
     * <li>Create suggestions : Camp Committee user creates suggestions for the camp through {@link CampComController#createSuggestion()}.
     * <li>View your suggestions : Camp Committee user view thier own suggestions for the camp through {@link CampComController#viewSuggestion()}.
     * <li>Delete your suggestions : Camp Committee user deletes their own suggestions for the camp through {@link CampComController#deleteSuggestion()}.
     * <li>Generate enquiries report : Camp Committee user creates suggestions for the camp through {@link CampManagementService#enquiriesReport()}.
     * <li>Generate attendence suggestions : Camp Committee user creates suggestions for the camp through {@link CampManagementService#generateStudentListReport()}, filterable by faculty or default.
     * <li>Switch to camp committe mode : Camp Committee user will switch into a student mode {@link Student}.
     * <li>Logout : Camp Committee user logs out from the system and returning to the {@link MainMenu}.
     * </ul><p>
     * Invalid inputs prompts the user to choose again.
     */
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
                        campComController.generateEnquiriesReport();
                        HelperService.pressAnyKeyToContinue();
                        break;
                    
                    case 8:    //generate student report
                        campComController.generateStudentReport();
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
