package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.data.Database;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;

/**
 * The {@link MainMenu} implements the generic menu, {@link Menu}. 
 * It provides methods to print the main menu and viewing the application 
 * 
 *  @author Wu Ji
 *  @version 1.0
 *  @since 2023-10-25
 */
public class MainMenu implements Menu{
    /**
     * Prints the main menu with welcome message and options to login or quit program
     */
    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Main Menu");
        ColouredTextPrinter.printGreen("Welcome to CAMs !!!");
        ColouredTextPrinter.printGreen("Press any number to login");
        ColouredTextPrinter.printGreen("Press 9 to quit program\n");
    }

    

    
    /**
     * Displays the main menu and read the user input for login or exitting the program
     * <p><ul>
     * <li>If the user chooses to log in, the user will be navigated to {@link LoginMenu}.
     * <li>If the user chooses to exit, the program will be terminated.
     * </ul><p>
     */
    public void viewApp(){
        Database.readData();
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            this.printMenu();
            choice = HelperService.readInt();

            if(choice!=9){
                LoginMenu login = new LoginMenu();
                login.viewApp();
            }
            else{
                System.out.println("Ending session...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
                System.exit(0);
            }
        }while(choice!=9);
    
        sc.close();
    }
}


