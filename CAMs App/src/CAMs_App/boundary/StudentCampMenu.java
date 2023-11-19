package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StudentController;
import CAMs_App.service.HelperService;

public class StudentCampMenu implements Menu{
    StudentController studentController = new StudentController();

    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Available actions for this camp: ");
        System.out.println("(1) Register camp");
        System.out.println("(2) Withdraw camp");
        System.out.println("(3) Submit enquiries for this camp");
        System.out.println("(4) View your submitted enquiries");
        System.out.println("(5) Logout\n");
    }

    

    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:    //register camp
                    System.out.println("You want to register as: ");
                    System.out.println("(1) Attendees");
                    System.out.println("(2) Camp Committee\n");
                    System.out.print("Enter your selection: ");
                    int choice1 = sc.nextInt();

                    if(choice1 == 1){
                        studentController.joinAsAttendee();    //update campName
                    }
                    else{
                        studentController.joinAsCommittee();
                    }
                    break;

                case 2:    //withdraw camp
                    studentController.withdrawCamp();
                    break;
                
                case 3:    //submit enquiry
                    studentController.createEnquiry();
                    break;
                
                case 4:    //view submitted enquiries
                    studentController.viewEnquiry();
                    break;

                case 5:    //logout
                    studentController.logout();
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 5);
    
        sc.close();

    }
}
