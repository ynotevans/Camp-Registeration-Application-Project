package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.CampComController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.*;
import CAMs_App.service.HelperService;

public class CampComMenu implements Menu{
    Student currentStudent = (Student)AuthData.getCurrentUser();
    CampComController campComController = new CampComController();

    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Welcome back "+ currentStudent.getUserID() + "!!!");
        System.out.println("Available actions for you: ");
        System.out.println("(1) View camp details");
        System.out.println("(2) View enquiries");
        System.out.println("(3) Reply enquiries");
        System.out.println("(4) Create suggestion");
        System.out.println("(5) View your suggestions");
        System.out.println("(6) Generate report");
        System.out.println("(7) Switch to student mode");
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
                    case 1:    //view camp details
                        campComController.viewCampDetails();
                        break;

                    case 2:    //view  enquiries
                        campComController.viewAllEnquiries();    //update the functions
                        break;
                
                    case 3:    //reply enquiries
                        campComController.replyEnquiry(0);   //update the functions
                        break;
                    
                    case 4:    //creata suggestion
                        campComController.createSuggestion();
                        break;
                    
                    case 5:    //view suggestions
                        campComController.viewSuggestion();
                        break;

                    case 6:    //generate report
                        campComController.generateReport();
                        break;
                    
                    case 7:    //switch mode
                        campComController.switchMode(2);
                        break;

                    case 8:    //logout
                        campComController.logout();
                        break;
                    
                    default:
                        System.out.println("Invalid selection, please select again...");
                        break;
                }

            }while(choice != 7);
        
            sc.close();

        }
}
