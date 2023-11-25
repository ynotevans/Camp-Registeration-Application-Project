package CAMs_App.boundary;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import CAMs_App.data.Database;
import CAMs_App.entity.Camp;
import CAMs_App.entity.Staff;
import CAMs_App.entity.Student;
import CAMs_App.service.ColouredTextPrinter;
import CAMs_App.service.HelperService;
import CAMs_App.enums.*;


public class MainMenu implements Menu{
    
    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Main Menu");
        ColouredTextPrinter.printGreen("Welcome to CAMs !!!");
        ColouredTextPrinter.printGreen("Press any number to login");
        ColouredTextPrinter.printGreen("Press 9 to quit program\n");
    }

    public void testData(){
        Map<String, Staff> user = Database.getStaffData();
        Map<String, Student> user1 = Database.getStudentsData();
        Map<String, Camp> camp = Database.getCampData();

        Staff staff1 = new Staff("WWW111", null, Faculty.NTU, "peter");
        Staff staff2 = new Staff("RRR222", null, Faculty.NTU, "peter");
        Student student3 = new Student("TTT444", "test", Faculty.EEE, "peter");
        Student student4 = new Student("YYY555", null, Faculty.CCEB, "peter");
        Student student5 = new Student("RRR222","password" , Faculty.SCSE, "peter");
        Student student6 = new Student("WWW333","password" , Faculty.CCEB, "peter");

        Student student7 = new Student("CCC111","password" , Faculty.SBS, "peter");
        Student student8 = new Student("CCC222","password" , Faculty.SOH, "peter");


        Camp camp1 = new Camp("camp1", LocalDate.of(2023, 10, 30), LocalDate.of(2023, 11, 3), 
        LocalDate.of(2023, 10, 15), Faculty.EEE , "SRC", 
        30 , 10, "EEE camp ", "WWW111", 
        true);

        Camp camp2 = new Camp("camp2", LocalDate.of(2023, 10, 31), LocalDate.of(2023, 11, 5), 
        LocalDate.of(2023, 10, 16), Faculty.SBS , "SRC", 
        30 , 10, "EEE camp ", "RRR222", 
        true);

        camp.put("camp1", camp1);
        camp.put("camp2", camp2);

        user.put("WWW111", staff1);
        user.put("RRR222", staff2);
        user1.put("TTT444", student3);
        user1.put("YYY555", student4);
        user1.put("RRR222", student5);
        user1.put("WWW333", student6);
        user1.put("CCC111", student7);
        user1.put("CCC222", student8);
        
        Database.setstaffData(user);
        Database.setStudentsData(user1);
        Database.setCampData(camp);
    }

    public void viewApp(){
        Database.readData();
        Scanner sc = new Scanner(System.in);
        int choice;

        do{
            this.printMenu();
            choice = sc.nextInt();

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


