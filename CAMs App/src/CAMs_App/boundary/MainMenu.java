package CAMs_App.boundary;

import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class MainMenu implements Menu{
    
    public void printMenu(){
        System.out.println("Welcome to CAMs !!!");
        System.out.println("(1) Login");
        System.out.println("(2) Change password");
        System.out.println("(3) Exit");
        System.out.println("");
        System.out.print("Enter ur selection: ");
    }

    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Prompt to Login...");
                break;

            case 2:
                System.out.println("Prompt to Change Password...");
                break;
            
            case 3:
                System.out.println("Prompt to Exit...");
                break;
            
            default:
                System.out.println("Invalid selection, please select again...");
                break;
        }


        sc.close();

    }
}
