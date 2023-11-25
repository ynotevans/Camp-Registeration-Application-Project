package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StaffController;
import CAMs_App.controllers.UserController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.User;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.HelperService;

public class StaffMenu implements Menu{
    User currentStaff = AuthData.getCurrentUser();
    StaffController staffController = new StaffController();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Staff Menu");
        ColouredTextPrinter.printBlue("Welcome back "+ currentStaff.getName() + "!!!");
        ColouredTextPrinter.printBlue("Available actions for you: ");
        ColouredTextPrinter.printBlue("(1) Create new camp");
        ColouredTextPrinter.printBlue("(2) View all camps");
        ColouredTextPrinter.printBlue("(3) View camp(s) created by you");
        ColouredTextPrinter.printBlue("(4) Change password");
        ColouredTextPrinter.printBlue("(5) Logout\n");
    }

    

    public void viewApp(){
        this.printMenu();
        
        Scanner sc = new Scanner(System.in);
        int choice;
        
            do{
                this.printMenu();
                System.out.print("Enter ur selection: ");
                choice = HelperService.readInt();

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
                        HelperService.pressAnyKeyToContinue();
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
                        HelperService.wait(3);
                        staffController.logout();
                        HelperService.wait(1);
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