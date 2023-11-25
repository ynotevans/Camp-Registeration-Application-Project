package CAMs_App.boundary;

// import java.util.ArrayList;
import java.util.Scanner;

import CAMs_App.controllers.StudentController;
import CAMs_App.controllers.UserController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.User;
import CAMs_App.entity.Student;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.HelperService;

public class StudentMenu implements Menu {
    User currentStudent = AuthData.getCurrentUser();
    StudentController studentController = new StudentController();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Student Menu");
        ColouredTextPrinter.printBlue("Welcome back "+ currentStudent.getName() + "!!!");
        HelperService.printStudentProfile();
        ColouredTextPrinter.printBlue("Available actions for you: ");
        ColouredTextPrinter.printBlue("(1) View available camps");
        ColouredTextPrinter.printBlue("(2) View registered camp(s)");
        ColouredTextPrinter.printBlue("(3) Switch to camp committee mode");
        ColouredTextPrinter.printBlue("(4) Change password");
        ColouredTextPrinter.printBlue("(5) Logout\n");
    }

    

    public void viewApp(){
        HelperService.clearScreen();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            
            this.printMenu();
            System.out.print("Enter selection: ");
            choice = HelperService.readInt();

            switch (choice) {
                case 1:    //view camp
                    if (studentController.viewAvailableCamp()==true){
                        System.out.print("Enter a camp name to operate, else just key in anything to return to menu: ");
                        String campName = sc.next();
                        if (DatabaseService.checkIfCampNameExists(campName) == false) {
                            System.out.println("Camp name does not exist. Going back to previous menu...");
                            HelperService.wait(1);
                            viewApp();

                        }
                        
                        else {

                            Camp selectedCamp = DatabaseService.getCamp(campName);
                            AuthData.setCurrentCamp(selectedCamp);
                            
                            if (selectedCamp.getCommittee().contains(currentStudent)){
                                System.out.println("Please toggle to camp committee menu in the menu...");
                                HelperService.wait(1);
                                viewApp();
                            }
                            
                            
                            else {
                                StudentCampMenu campMenu = new StudentCampMenu();
                                campMenu.viewApp();
                            }
                        } 
                    }

                    else{
                         System.out.println("Currently no available camp(s) for you.\n");
                         HelperService.wait(1);
                    }
                    
                    break;

                case 2:    //view all registered camp
                    Student currentStudent = (Student)AuthData.getCurrentUser();
                    if (studentController.viewRegisteredCamp()==true){
                        System.out.print("Select a camp to operate(Or any key to go back to previous page): ");
                        String campName = sc.next();
                        
                        
                        if (DatabaseService.checkIfCampNameExists(campName) == false) {
                            System.out.println("Camp name does not exist. Going back to previous menu...");
                            HelperService.wait(1);
                            viewApp();
                        }
                        
                        else {
                            Camp selectedCamp = DatabaseService.getCamp(campName);
                            AuthData.setCurrentCamp(selectedCamp);
                            
                            if (selectedCamp.getCommittee().contains(currentStudent)){
                                System.out.println("Please toggle to camp committee menu in the menu...");
                                HelperService.pressAnyKeyToContinue();
                                viewApp();
                            }
                            
                            else {
                                StudentCampMenu campMenu = new StudentCampMenu();
                                // edit camp function
                                HelperService.wait(1);
                                campMenu.viewApp();
                            }
                        } 
                    }

                    else {
                            System.out.println("Currently no registered camp(s) for you. Going back previous menu...\n");
                            HelperService.wait(2);
                    }
                    
                    break;

                case 3:    //switch mode
                    studentController.switchMode(1);
                    System.out.println("Sorry, you are not a camp committee. Going back previous menu...");
                    HelperService.wait(3);
                    break;
                
                 case 4:    //change password
                    HelperService.clearScreen();
                    HelperService.printRoute("Student Menu ---> Change Password");
                    UserController.changePassword();
                    HelperService.wait(1);
                    this.viewApp();
                    break;

                case 5:    //logout
                    System.out.println("Logging out...");
                    HelperService.wait(3);
                    studentController.logout();
                    HelperService.wait(1);
                    MainMenu comMenu = new MainMenu();
                    comMenu.viewApp();
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    HelperService.wait(1);
                    break;
            }

        }while(choice != 4);
    
        sc.close();

    }
}
