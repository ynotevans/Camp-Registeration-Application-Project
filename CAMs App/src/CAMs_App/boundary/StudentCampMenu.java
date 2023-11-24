package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StudentController;
import CAMs_App.entity.Student;
import CAMs_App.service.HelperService;

public class StudentCampMenu implements Menu{
    StudentController studentController = new StudentController();
    StudentMenu studentMenu = new StudentMenu();
    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Available actions for this camp: ");
        System.out.println("(1) Register camp");
        System.out.println("(2) Withdraw camp");
        System.out.println("(3) Submit enquiries for this camp");
        System.out.println("(4) View your submitted enquiries");
        System.out.println("(5) Go to previous page");
        System.out.println("(6) Logout\n");
    }

    

    public void viewApp(){
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            this.printMenu();
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
                        HelperService.clearScreen();
                        studentController.joinAsAttendee();    //update campName

                    }
                    else{
                        studentController.joinAsCommittee();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    studentMenu.viewApp();
                    break;

                case 2:    //withdraw camp
                    studentController.withdrawCamp();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                
                case 3:    //submit enquiry
                    studentController.createEnquiry();
                    break;
                
                case 4:    //view submitted enquiries
                    studentController.viewEnquiry();
                    System.out.print("Key in anything to return to menu: ");
                    sc.next();
                    
                    break;

                case 5: //go to previous page
                    studentMenu.viewApp();
                    break;

                case 6:    //logout
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

        }while(choice != 5);
    
        sc.close();

    }
}
