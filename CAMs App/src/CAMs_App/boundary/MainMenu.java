package CAMs_App.boundary;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import CAMs_App.data.Database;
import CAMs_App.entity.Staff;
import CAMs_App.entity.Student;
import CAMs_App.service.HelperService;


import CAMs_App.enums.*;

public class MainMenu implements Menu{
    
    public void printMenu(){
        HelperService.clearScreen();
        System.out.println("Welcome to CAMs !!!");
        System.out.println("(1) Login");
        System.out.println("(2) Change password");
        System.out.println("(3) Exit\n");
    }

    public void testData(){
        Map<String, Staff> user = new HashMap<String, Staff>();
        Map<String, Student> user1 = new HashMap<String, Student>();
        Staff staff1 = new Staff("WWW111", null, Faculty.SCSE);
        Staff staff2 = new Staff("RRR222", null, Faculty.EEE);
        Student staff3 = new Student("TTT444", null, Faculty.SCSE);
        Student staff4 = new Student("YYY555", null, Faculty.CCEB);

        user.put("WWW111", staff1);
        user.put("RRR222", staff2);
        user1.put("TTT444", staff3);
        user1.put("YYY555", staff4);
        
        Database.setstaffData(user);
        Database.setStudentsData(user1);
    }

    public void viewApp(){
        this.testData();
        this.printMenu();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter ur selection: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Prompt to Login...");
                    LoginMenu login = new LoginMenu();
                    login.viewApp();
                    break;

                case 2:
                    System.out.println("Prompt to Change Password...");
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
