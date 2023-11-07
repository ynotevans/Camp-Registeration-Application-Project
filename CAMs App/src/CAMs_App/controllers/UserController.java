package CAMs_App.controllers;
import java.util.Scanner;

public class UserController {
    private static final Scanner sc = new Scanner(System.in);

    public UserController(){}

    protected boolean changePassword(){
        String oldPassword, newPassword;
        boolean success = false;
        System.out.println("Changing Password...");

        do {
            System.out.println("Enter your old password (Enter X to quit): ");
            oldPassword = sc.next();
            if (oldPassword.equalsIgnoreCase("X")) {
                System.out.println("Exiting change password... ");
                return false;
            }

            System.out.println("Enter your new password: ");
            newPassword = sc.next();

            if (newPassword.equals(oldPassword)){
                System.out.println("New password cannot be the same as old password.");
                continue;
            }

            // success = userService.changePassword(oldPassword,newPassword);

            if (!success) {
                System.out.println("Old password does not match.");
            }

        } while (!success);

        System.out.println("Password successfully changed.");
        return true;
    }
}