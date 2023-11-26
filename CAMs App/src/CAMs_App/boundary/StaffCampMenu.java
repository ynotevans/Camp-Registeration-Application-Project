package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.data.AuthData;
import CAMs_App.service.HelperService;
import CAMs_App.service.CampManagementService;
import CAMs_App.service.ColouredTextPrinter;
/**
 * The {@link StaffCampMenu} implements the generic menu, {@link Menu}. 
 * It provides methods to print the main menu and viewing the application.
 * The class uses {@link StaffController} to manage staff-related activities and 
 * also uses {@link CampManagementService} in generating reports.
 * 
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public class StaffCampMenu implements Menu {
    /** The controller for staff-related activities */
    StaffController staffController = new StaffController();
    /**
     * The printMenu() method displays the camp menu options available for staff user.
     * It prints camp menu options for staff user such as edit camp, delete camp, 
     * toggle camp visibility, view enquiries, reply enquiries, view suggestions, 
     * process suggestions, approve suggestions, generate report, returning to previous page and logout.
     */
    public void printMenu(){
        HelperService.clearScreen();
        HelperService.clearScreen();
        HelperService.printRoute("Staff Camp Menu ---> "+ AuthData.getCurrentCamp().getCampName());
        ColouredTextPrinter.printBlue("Available actions for this camp: ");
        ColouredTextPrinter.printBlue("(1) Edit camp");
        ColouredTextPrinter.printBlue("(2) Delete camps");
        ColouredTextPrinter.printBlue("(3) Toggle camp's visibility");
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(4) View enquiries for this camp");
        ColouredTextPrinter.printBlue("(5) Reply enquiries for this camp");
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(6) View suggestions for this camp");
        ColouredTextPrinter.printBlue("(7) Process suggestions for this camp");
        ColouredTextPrinter.printBlue("(8) Approve suggestions for this camp");
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(9) View / Generate report");
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(10) Previous Page");
        ColouredTextPrinter.printBlue("(11) Logout\n");
    }

    
    /**
     * The viewApp() method allows Staff user to perform various camp-related actions for the current camp such as 
     * <p><ul>
     * <li>Edit camp : Staff user edits the current camp infomation through {@link StaffController#editCamp()}.
     * <li>Delete camp : Staff user deletes the current camp through {@link StaffController#deleteCamp()}.
     * <li>Toggle camp's visibility : Staff user toggles the current camp visibility through {@link StaffController#toggleVisibility()}.
     * <li>View enquiries for this camp : Staff user views the current camp's enquiries through {@link StaffController#viewEnquiries()}.
     * <li>Reply enquiries for this camp : Staff user replies the current camp's enquiries through {@link StaffController#replyEnquiries()}.
     * <li>View suggestions for this camp : Staff user views the current camp's suggestions through {@link StaffController#viewSuggestions()}.
     * <li>Process suggestions for this camp : Staff user processes the current camp's suggestions through {@link StaffController#processSuggestions()}.
     * <li>Approves suggestions for this camp :  Staff user approves the current camp's suggestsions through {@link StaffController#approveSuggestion()}.
     * <li>Generate report : Staff user can either generate {@link StaffController#generateCommitteeReport()}, {@link StaffController#generateStudentReport()} or{@link StaffController#generateEnquiriesReport()}. 
     * These reports can be filtered to faculty or default (print all) by user input.
     * <li>Go to previous page : Returns the Staff user back to {@link StaffMenu}.
     * <li>Logout : Staff user logs out from the system and returning to the {@link MainMenu}.
     * </ul><p>
     * Invalid inputs prompts the user to choose again.
     */
    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
            do{
                System.out.print("Enter ur action for this camp: ");
                choice = HelperService.readInt();
                switch (choice) {
                    case 1:    //edit camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Edit Camp");
                        staffController.editCamp();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;

                    case 2:    //delete camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Delete Camp");
                        staffController.deleteCamp();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;
                    
                    case 3:    //toggle camp visibility
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Toggle Camp Visibility");
                        staffController.toggleVisibility();
                        HelperService.pressAnyKeyToContinue();
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
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;

                    case 6:    //view suggestion
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> View Suggestions");
                        staffController.viewSuggestions();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;

                    case 7:    //process suggestion
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Process Suggestions");
                        staffController.processSuggestions();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;
                    
                    case 8:    //approve suggestion
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Approve Suggestions");
                        staffController.approveSuggestion();
                        HelperService.pressAnyKeyToContinue();
                        
                        this.viewApp();
                        break;
                    
                    case 9:  //generate report
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Camp Menu ---> Print report");
                        System.out.println("Select the report you want to generate");
                        System.out.println("(1) Committee peformance report");
                        System.out.println("(2) Student report");
                        System.out.println("(3) Enquiries report");
                        System.out.println("(4) Camp report");
                        System.out.println("(5) Stop generating report");
                        
                        int report = HelperService.readInt(1,5,"Invalid choice please try again");
                        System.out.println("Do you want to view here or generate to a file report(1 to view, any number to generate)");
                        int generate = HelperService.readInt();
                        switch (report){
                            case 1:
                                HelperService.clearScreen();
                                HelperService.printRoute("Staff Camp Menu ---> Print Committee Performance Report");
                                if(generate == 1)
                                    staffController.generateCommitteeReport();
                                else
                                    staffController.committeeReportFile();
                                
                                HelperService.pressAnyKeyToContinue();   
                                break;
                            
                            case 2:
                                HelperService.clearScreen();
                                HelperService.printRoute("Staff Camp Menu ---> Print Student Report");
                                if(generate == 1)
                                    staffController.generateStudentReport();
                                else
                                    staffController.studentReportFile();
                                HelperService.pressAnyKeyToContinue();
                                break;
                            
                            case 3:
                                HelperService.clearScreen();
                                HelperService.printRoute("Staff Camp Menu ---> Print Enquiries Report");
                                if(generate == 1)
                                    staffController.generateEnquiriesReport();
                                else
                                    staffController.EnquiriesReportFile();

                                HelperService.pressAnyKeyToContinue();
                                break;

                            case 4:
                                HelperService.clearScreen();
                                HelperService.printRoute("Staff Camp Menu ---> Print Camp Report");
                                if(generate == 1)
                                    staffController.generateCampReport();
                                else
                                    staffController.CampReportFile();
                                HelperService.pressAnyKeyToContinue();
                                break;


                            default:
                             break;
                        }

                        this.viewApp();
                        
                    
                    case 10: //go to previous page
                        StaffMenu staffMenu = new StaffMenu();
                        staffMenu.viewApp();
                        break;
                                    
                    case 11:    //logout
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
           
            }while(choice != 12);
        
            sc.close();

        }
}
