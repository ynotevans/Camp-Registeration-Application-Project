package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.StudentController;
import CAMs_App.controllers.UserController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.Camp;
import CAMs_App.entity.CampComMem;
import CAMs_App.entity.User;
import CAMs_App.entity.Student;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.DatabaseService;
import CAMs_App.service.HelperService;
/**
 * The {@link StudentMenu} implements the generic menu, {@link Menu}. 
 * It provides methods to print the main menu and viewing the application.
 * The class uses {@link StudentController} to manage student-related activities.
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class StudentMenu implements Menu {
    /** The currently logged-in Student user. */
    User currentStudent = AuthData.getCurrentUser();
    /** The controller for student-related activities */
    StudentController studentController = new StudentController();
    /**
     * The printMenu() method displays the menu options available for student user.
     * It prints student menu options such as view available camps, view registered camp(s), switch to camp committee mode, change password and logout.
     */
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

    
    /**
     * The viewApp() method allows Student user to perform various actions such as 
     * <p><ul>
     * <li>View available camps : Student user view the available camps by {@link StudentController#viewAvailableCamp()}. 
     * If a valid camp is entered in the list of available camps, the Student user will be navigated to {@link StudentCampMenu} 
     * or be prompted to switch mode to {@link CampComMem} if the selected camp is already registered as camp committee.
     * <li>View registered camp(s) : Student user views the camp's that they have registered by {@link StudentController#viewRegisteredCamp()}. 
     * If the Student user registered a camp and selects a valid campname in the list, the Student user will be navigated to {@link StudentCampMenu} or be prompted to switch mode to {@link CampComMem}.
     * <li>Switch to camp committe mode : Student user will switch into a camp committee mode {@link CampComMem}.
     * <li>Changing password : Student user changes password via {@link UserController#changePassword()}.
     * <li>Logging out : Student user logs out from the system and returning to the {@link MainMenu}.
     * </ul><p>
     * Invalid inputs prompts the user to choose again.
     */
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
                HelperService.clearScreen();
                HelperService.printRoute("Student Menu ---> View available camp");
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
                HelperService.clearScreen();
                HelperService.printRoute("Student Menu ---> View registered camp");
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

        }while(choice != 7);
    
        sc.close();

    }
}
