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
        HelperService.clearScreen();
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
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            
            this.printMenu();
            System.out.print("Enter selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:    //view camp
                    if (studentController.viewAvailableCamp()==true){
                        System.out.print("Enter a camp name to operate, else just key in anything to return to menu: ");
                        String campName = sc.next();
                        if (DatabaseService.checkIfCampNameExists(campName) == false) {
                            System.out.println("Camp name does not exist. Going back to previous menu.");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                            e.printStackTrace();
                            }
                            viewApp();

                        }
                        
                        else {
                            Camp selectedCamp = DatabaseService.getCamp(campName);
                            AuthData.setCurrentCamp(selectedCamp);
                            StudentCampMenu campMenu = new StudentCampMenu();
                            campMenu.viewApp();
                        } 
                    }

                    else{
                         System.out.println("Currently no available camp(s) for you.\n");
                    }

                    break;

                case 2:    //view all registered camp
                    if (studentController.viewRegisteredCamp()==true){

                        System.out.print("Select a camp to edit (Enter the camp name): ");
                        String campName = sc.next();

                        if(DatabaseService.checkCampName(campName, currentStudent.getUserID())){
                            Camp selectedCamp = DatabaseService.getCamp(campName);
                            AuthData.setCurrentCamp(selectedCamp);
                        }

                        else {
                            if (DatabaseService.checkIfCampNameExists(campName) == false) {
                                System.out.println("Camp name does not exist. Going back to previous menu.");
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                e.printStackTrace();
                                }
                                viewApp();
                            }
                        }

                    }

                    else {
                            System.out.println("Currently no registered camp(s) for you.\n");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }

                    break;

                case 3:    //switch mode
                    
                    studentController.switchMode(1);
                    System.out.println("Sorry, you are not a camp committee. Going back previous menu...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:    //logout
                    System.out.println("Logging out...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainMenu comMenu = new MainMenu();
                    comMenu.viewApp();
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 4);
    
        sc.close();

    }
}
