package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.UserController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.User;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;
/**
 * The {@link LoginMenu} implements the generic menu, {@link Menu}.
 * It provides methods to print the main menu and viewing the application 
 * 
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public class LoginMenu implements Menu {
    /**
     * Prints the login menu with options for Staff or Student, and option to quit.
     */
    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Login Menu");
        ColouredTextPrinter.printGreen("Login as: ");
        ColouredTextPrinter.printGreen("(1) Staff ");
        ColouredTextPrinter.printGreen("(2) Student ");
        ColouredTextPrinter.printGreen("(3) Exit \n");
        
    }

    /**
     * Displays the login menu and reads the user input for role selection or exitting program. 
     * This method uses a switch case statements to handle the user inputs.
     * <p><ul>
     * <li>1 - Staff login: Initiates staff login and navigates to the {@link StaffMenu}.
     * <li>2 - Student login: Initiates student login and navigates to the {@link StudentMenu}
     * <li>3 - Exit program: Ends the session and terminates the program.
     * <li>Invalid selections prompt the user to choose again.
     * </ul><p>
     * Users are given total of 5 attempts for login.
     * If the user is logged in for the first time, the user will be prompted to change password.
     */
    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        UserController userController=new UserController();
        
        do{
            System.out.print("Enter ur selection: ");
            choice = HelperService.readInt();
            
            switch (choice) {
                case 1:  //staff login
                System.out.println("(Maximum 5 login attempts)");
                if (userController.login(true)){
                            User user = (User)AuthData.getCurrentUser();
                            if (user.getPassword().equals("password")){
                                System.out.println("\nFor first time login, please change password. ");
                                UserController.changePassword();
                            }
                            HelperService.wait(2);
                            StaffMenu staffMenu = new StaffMenu();
                            staffMenu.viewApp();
                        }
                        HelperService.wait(1);
                        viewApp();
                        break;

                    case 2:   //student login
                        System.out.println("(Maximum 5 login attempts)");
                        if(userController.login(false)) {
                            User user = (User)AuthData.getCurrentUser();
                            if (user.getPassword().equals("password")){
                                System.out.println("\nFor first time login, please change password. ");
                                UserController.changePassword();
                            }
                            HelperService.wait(2);
                            StudentMenu studentMenu = new StudentMenu();
                            studentMenu.viewApp();
                        }
                        HelperService.wait(1);
                        viewApp();
                        break;
                    
                    case 3:
                        break;
                    
                    default:
                        System.out.println("Invalid selection, please select again...");
                        HelperService.wait(1);
                        viewApp();
                        break;
                        
                }

            }while(choice != 3);

            sc.close();
        }
       
}
