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
/**
 * The {@link StaffMenu} implements the generic menu, {@link Menu}. 
 * It provides methods to print the main menu and viewing the application.
 * The class uses {@link StaffController} to manage staff-related activities 
 * 
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public class StaffMenu implements Menu{
    /** The currently logged-in Staff user. */
    User currentStaff = AuthData.getCurrentUser();
    /** The controller for staff-related activities */
    StaffController staffController = new StaffController();
    /**
     * The printMenu() method displays the menu options available for staff user.
     * It prints staff menu options such as creating a new camp, viewing all camps, viewing camps created by the staff user, changing password and logging out.
     */
    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Staff Menu");
        ColouredTextPrinter.printBlue("Welcome back "+ currentStaff.getName() + "!!!");
        ColouredTextPrinter.printBlue("Available actions for you: ");
        ColouredTextPrinter.printBlue("(1) Create new camp");
        ColouredTextPrinter.printBlue("(2) View all camps");
        ColouredTextPrinter.printBlue("(3) View camp(s) created by you");
        ColouredTextPrinter.printBlue("(4) Change password");
        ColouredTextPrinter.printBlue("(5) Logout");
    }

    
    /**
     * The viewApp() method allows Staff to perform various actions such as 
     * <p><ul>
     * <li>Creating a camp : Staff user will be navigated to {@link StaffController#createCamp()} method
     * <li>Viewing all camps : Staff user will be navigated to {@link StaffController#viewAllCamp()} method
     * <li>Viewing camps created by the staff member : Displays the camps made by Staff user {@link StaffController#viewCreatedCamp(String)}.
     * Staff user will be navigated to {@link StaffCampMenu} after entering a valid camp.
     * <li>Changing password : Staff user changes password via {@link UserController#changePassword()}.
     * <li>Logging out : Staff user logs out from the system and returning to the {@link MainMenu}.
     * </ul></p>
     * Invalid inputs prompts the user to choose again.
     */
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
                        HelperService.wait(2);
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
                            System.out.println("Camp does not exist. ");
                            HelperService.wait(2);
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

            }while(choice != 7);
        
            sc.close();

        }
}