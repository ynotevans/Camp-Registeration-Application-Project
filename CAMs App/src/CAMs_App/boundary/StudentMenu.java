package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StudentController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.User;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.HelperService;

public class StudentMenu implements Menu {
    User currentStudent = AuthData.getCurrentUser();
    StudentController studentController = new StudentController();

    public void printMenu(){
        System.out.println("Welcome back "+ currentStudent.getUserID() + "!!!");
        System.out.println("Student profile reflected here....");
        System.out.println("Available actions for you: ");
        System.out.println("(1) View available camps");
        System.out.println("(2) View registered camp(s)");
        System.out.println("(3) Switch to camp committee mode");
        System.out.println("(4) Logout\n");
    }

    

    public void viewApp(){
        HelperService.clearScreen();
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:    //create camp
                    studentController.viewAvailableCamp();
                    break;

                case 2:    //view all camp
                    if (studentController.viewRegisteredCamp()==true){

                        System.out.print("Select a camp to operate (campName): ");
                        String campName = sc.next();

                        if(DatabaseService.checkCampName(campName, currentStudent.getUserID())){
                            Camp selectedCamp = DatabaseService.getCamp(campName);
                            AuthData.setCurrentCamp(selectedCamp);
                        }

                    }

                    else {
                            System.out.println("Currently no available camp for you.");
                    }

                    break;

                case 3:    //switch mode
                    studentController.switchMode(1);
                    break;

                case 4:    //logout
                    studentController.logout();
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 4);
    
        sc.close();

    }
}
