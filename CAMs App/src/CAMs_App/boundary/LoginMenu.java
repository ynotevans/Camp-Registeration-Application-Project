package CAMs_App.boundary;

import java.security.cert.TrustAnchor;
import java.util.Scanner;

import CAMs_App.service.AuthStaffService;
import CAMs_App.service.AuthStudentService;
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
        boolean authenticated = false;
        String userID, password;

        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AuthStaffService authStaffService = new AuthStaffService();
                    sc.nextLine();
                    System.out.print("User ID: ");
                    userID = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();
                    authenticated = authStaffService.login(userID, password);

                    while(authenticated != true){               
                        System.out.println("Wrong UserID or Password, please enter again...");
                        System.out.print("User ID: ");
                        userID = sc.nextLine();
                        System.out.print("Password: ");
                        password = sc.nextLine();
                        authenticated = authStaffService.login(userID, password);
                    }

                    System.out.println("Login success");
                    StaffMenu staffMenu = new StaffMenu();
                    staffMenu.viewApp();
                    
                    break;

                case 2:
                    AuthStudentService authStudentService = new AuthStudentService();
                    sc.nextLine();
                    System.out.print("User ID: ");
                    userID = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();
                    authenticated = authStudentService.login(userID, password);

                    while(authenticated != true){               
                        System.out.println("Wrong UserID or Password, please enter again...");
                        System.out.print("User ID: ");
                        userID = sc.nextLine();
                        System.out.print("Password: ");
                        password = sc.nextLine();
                        authenticated = authStudentService.login(userID, password);
                    }

                    System.out.println("Login success");
                    break;
                
                case 3:
                    System.out.println("Prompt to Exit...");
                    System.exit(0);
                    break;
                
                default:
                    System.out.println("Invalid selection, please select again...");
                    break;
            }

        }while(choice != 3);
    
        sc.close();
    }
}
