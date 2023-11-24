package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.UserController;
import CAMs_App.service.HelperService;

public class LoginMenu implements Menu {
    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Login as:");
        System.out.println("(1) Staff");
        System.out.println("(2) Student");
        System.out.println("(3) Exit\n");
        
    }

    
    public void viewApp(){
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        UserController userController=new UserController();

        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:  //staff login
                    System.out.println("(Maximum number of login attempts is 5)");
                    if (userController.login(true)){

                        StaffMenu staffMenu = new StaffMenu();
                        staffMenu.viewApp();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException error) {
                        error.printStackTrace();
                    }
                    viewApp();
                    break;

                case 2:   //student login
                    System.out.println("(Maximum number of login attempts is 5)");
                    if(userController.login(false)) {
                        StudentMenu studentMenu = new StudentMenu();
                        studentMenu.viewApp();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException error) {
                        error.printStackTrace();
                    }
                    viewApp();
                    break;
                
                case 3:
                    System.out.println("Prompt to Exit...");
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 3);

        sc.close();
    }

}
