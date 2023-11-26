package CAMs_App.controllers;
import java.util.Scanner;

import CAMs_App.service.AuthStaffService;
import CAMs_App.service.AuthStudentService;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;
import CAMs_App.boundary.LoginMenu;
import CAMs_App.data.AuthData;
import CAMs_App.entity.User;
import CAMs_App.service.AuthService;
import CAMs_App.service.UserService;

/**
 * The {@link UserController} class is responsible for handling user-related
 * actions, such as changing the user's password. This
 * class serves as a base class for more specific user types like
 * {@link StudentController} or {@link StaffController}.
 * 
 *  @author Liang Meng
 *  @version 1.0
 *  @since 2023-10-25
 */
public class UserController {
	/**
	 * {@link Scanner} object to get input from the user.
	 */
    public static final Scanner sc = new Scanner(System.in);
    /**
     * {@link authService} object for methods for logging in and out.
     */
    AuthService authService;
    
    /**
     * Constructs an instance of the {@link UserController} class.
     */
    public UserController(){}

    /**
     * Changes the users password by prompting them to enter their old and new password.
     * If the old password is correct and the new password is different,
     * the change is successful.
     * 
     * The password change fails in the following scenarios:
     * <ol>
     * <li>The old password does not match the current password</li>
     * <li>The new password is the same as the old password</li>
     * <li>The user decides to quit the process by entering "X"</li>
     * </ol>
     * 
     */
    public static void changePassword(){
        String oldPassword, newPassword;
        boolean success = false;
        
        do {
            User user = (User)AuthData.getCurrentUser();
            System.out.println("Enter your old password (Enter X to quit): ");
            oldPassword = sc.next();
            
            if (oldPassword.equalsIgnoreCase("X")) {
                System.out.println("Exiting change password... ");
                HelperService.wait(1);
                break;
            }
            if (!(oldPassword.equals(user.getPassword()))){
                System.out.println("Incorrect old password, exiting...");
                HelperService.wait(1);
                break;
            }

            System.out.println("Enter your new password: ");
            newPassword = sc.next();
            if (newPassword.equals(oldPassword)){
                System.out.println("New password cannot be the same as old password.");
                continue;
            }
            
            success = UserService.changePassword(oldPassword,newPassword);
            if (!success) {
                System.out.println("Old password does not match.");
            }
            else
            System.out.println("Password successfully changed.");
            System.out.println("Please re-login to verify.");
            HelperService.pressAnyKeyToContinue();
        } while (!success);
        UserController userController = new UserController();
        userController.logout();
        HelperService.wait(1);
        LoginMenu menu = new LoginMenu();
        menu.viewApp();
    }
    
    /**
     * Authenticates the user by prompting them to enter their credentials.
     * Then calls the login method from {@link authStaffService} or {@link authStudentService}.
     * The user has four attempts to enter the correct User ID and password.
     * 
     * @param isStaff Indicates whether the user is logging in as a staff or student.
     * 
     * @return {@code true} if login successful, {@code false} if otherwise.
     */
    public boolean login(boolean isStaff){
        int attempt=0;
        if(isStaff){
           authService = new AuthStaffService();
        }
        else{
           authService = new AuthStudentService();
        }

        System.out.print("User ID: ");
        String userID = sc.next();
        System.out.print("Password: ");
        String password = sc.next();
        boolean authenticated = authService.login(userID, password);

        while(authenticated != true){  
            attempt++;        
            ColouredTextPrinter.printRed("Attempt " + attempt + ": Wrong UserID or Password, please enter again...");
            System.out.print("User ID: ");
            userID = sc.next();
            System.out.print("Password: ");
            password = sc.next();
            authenticated = authService.login(userID, password);
            if (attempt == 4) {
                ColouredTextPrinter.printRed("You have reached the maximum number of attempts.");
                return false;
            }
        }

        System.out.println("Login success"); 
        return true;      
    }

    /**
     * Calls the logout method from the corresponding {@link authService} object.
     */
    public void logout(){
        AuthService.logout();
        System.out.println("Successfully logout");
     }

}