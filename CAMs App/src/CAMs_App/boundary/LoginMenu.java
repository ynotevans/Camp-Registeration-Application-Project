package CAMs_App.boundary;

import java.util.Scanner;

import CAMs_App.controllers.UserController;
import CAMs_App.data.AuthData;
import CAMs_App.entity.User;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;

public class LoginMenu implements Menu {
    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Login Menu");
        ColouredTextPrinter.printGreen("Login as: ");
        ColouredTextPrinter.printGreen("(1) Staff ");
        ColouredTextPrinter.printGreen("(2) Student ");
        ColouredTextPrinter.printGreen("(3) Exit \n");
        
    }

    
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
                        System.out.println("Prompt to Exit...");
                        System.exit(0);
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
