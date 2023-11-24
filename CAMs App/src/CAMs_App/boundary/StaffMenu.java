package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.controllers.UserController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.User;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.HelperService;

public class StaffMenu implements Menu{
    User currentStaff = AuthData.getCurrentUser();
    StaffController staffController = new StaffController();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Staff Menu");
        System.out.println("Welcome back "+ currentStaff.getName() + "!!!");
        System.out.println("Available actions for you: ");
        System.out.println("(1) Create new camp");
        System.out.println("(2) View all camps");
        System.out.println("(3) View camp(s) created by you");
        System.out.println("(4) Change password");
        System.out.println("(5) Logout\n");
    }

    

    public void viewApp(){
        this.printMenu();
        
        Scanner sc = new Scanner(System.in);
        int choice;
        
            do{
                this.printMenu();
                System.out.print("Enter ur selection: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:    //create camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Menu ---> Create Camp");
                        staffController.createCamp();
                        break;

                    case 2:    //view all camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Menu ---> View All Camp");
                        staffController.viewAllCamp();
                        break;
                    
                    case 3:    //view staff created camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Menu ---> View Camp Created By You");
                        staffController.viewCreatedCamp(currentStaff.getUserID());

                        System.out.print("Select a camp to operate (campName): ");
                        String campName = sc.next();

                        if(DatabaseService.checkCampName(campName, currentStaff.getUserID())){
                            Camp selectedCamp = DatabaseService.getCamp(campName);
                            AuthData.setCurrentCamp(selectedCamp);

                            StaffCampMenu campMenu = new StaffCampMenu();   //not here
                            campMenu.viewApp();
                        }
                        else{
                            System.out.println("Camp not exist, please enter the correct camp Name... ");
                        }
                        
                        
                        break;
                    
                    case 4:    //change password
                        HelperService.clearScreen();
                        HelperService.printRoute("Staff Menu ---> Change Password");
                        UserController.changePassword();
                        HelperService.wait(1);
                        this.viewApp();
                        break;
                    
                    case 5:    //logout
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

            }while(choice != 4);
        
            sc.close();

        }
}