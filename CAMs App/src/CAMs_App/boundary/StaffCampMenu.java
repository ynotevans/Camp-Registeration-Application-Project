package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.data.AuthData;
import CAMs_App.service.HelperService;
import CAMs_App.service.StaffCampService;
import CAMs_App.service.CampManagementService;
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
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(4) View enquiries for this camp");
        ColouredTextPrinter.printBlue("(5) Reply enquiries for this camp");
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(6) View suggestions for this camp");
        ColouredTextPrinter.printBlue("(7) Process suggestions for this camp");
        ColouredTextPrinter.printBlue("(8) Approve suggestions for this camp");
        ColouredTextPrinter.printBlue("-------------------------------------------");
        ColouredTextPrinter.printBlue("(9) Generate report");
        ColouredTextPrinter.printBlue("------------------------------------------");
        ColouredTextPrinter.printBlue("(10) Previous Page");
        ColouredTextPrinter.printBlue("(11) Logout\n");
    }

    

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
                        System.out.println("(3) Camp participants(Committees & attendees) report");
                        System.out.println("(4) Enquiries report");
                        System.out.println("(5): Stop generating report");
                        
                        int report = HelperService.readInt(1,5,"Invalid choice please try again");
                        
                        switch (report) {
                            case 1:
                            System.out.println("Press 1 Filter report by faculty. Else generate by default");
                            int filter = HelperService.readInt(); 
                                if(filter == 1){
                                    System.out.println("Choose the faculty");
                                    String fac = sc.nextLine();
                                    StaffCampService.commiteePerformanceReport(fac);
                                }
                                else{
                                    StaffCampService.commiteePerformanceReport();
                                }
                                
                                break;
                            
                            case 2:
                            System.out.println("Press 1 Filter report by faculty. Else generate by default");
                            filter = HelperService.readInt(); 
                                if(filter == 1){
                                    System.out.println("Choose the faculty");
                                    String fac = sc.nextLine();
                                    CampManagementService.generateStudentListReport(fac);
                                }
                                else{
                                    CampManagementService.generateStudentListReport();
                                }
                                

                            case 3:
                            System.out.println("Press 1 Filter report by faculty. Else generate by default");
                            filter = HelperService.readInt(); 
                                if(filter == 1){
                                    System.out.println("Choose the faculty");
                                    String fac = sc.nextLine();
                                    StaffCampService.commiteePerformanceReport(fac);
                                    System.out.println(" ");
                                    CampManagementService.generateStudentListReport(fac);
                                }
                                else{
                                    StaffCampService.commiteePerformanceReport();
                                    System.out.println(" ");
                                    CampManagementService.generateStudentListReport();
                                }
                            break;

                            case 4:
                            CampManagementService.enquiriesReport();
                            break;

                            default:
                             break;
                        }
                        staffController.generateCommitteeReport();
                        HelperService.pressAnyKeyToContinue();
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
