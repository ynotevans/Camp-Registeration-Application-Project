package CAMs_App.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import CAMs_App.data.AuthData;
import CAMs_App.data.Database;
import CAMs_App.entity.*;
import java.util.Scanner;

public class HelperService {
    static Scanner sc = new Scanner(System.in);

    /**
     * Method to clear the screen of the terminal for user experience and neat
     * interface.
    */
    public static void clearScreen() {
        try {
          new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception err) {}
    } 

  
    public static void printRoute(String route) {
      int totalWidth = 105; // Adjust this based on the total width of your console or window
  
      int spacesNeeded = Math.max(0, totalWidth - route.length());
      int leftSpaces = spacesNeeded / 2;
      int rightSpaces = spacesNeeded - leftSpaces + 1;
  
      String leftPadding = String.format("%" + leftSpaces + "s", "");
      String rightPadding = String.format("%" + rightSpaces + "s", "");
  
      ColouredTextPrinter.printPurple(
              "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
              ColouredTextPrinter.printPurple("║" + leftPadding + route + rightPadding + "║");
      ColouredTextPrinter.printPurple(
              "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
  }
  
    public static void printStudentProfile(){
      Student student = (Student)AuthData.getCurrentUser();
      ColouredTextPrinter.printCyan("\n-------------------------");
      ColouredTextPrinter.printCyan("  Your student profile");
      ColouredTextPrinter.printCyan("Name: " + student.getName());
      ColouredTextPrinter.printCyan("UserID: " + student.getUserID());
      ColouredTextPrinter.printCyan("Faculty: " + student.getFaculty());
      if(student.getIsComittee()){
      ColouredTextPrinter.printCyan("You are a committee member of " + student.getComitteeCamp().getCampName());
      ColouredTextPrinter.printCyan("Position held: " + student.getCampComMem().getPosition());
      ColouredTextPrinter.printCyan("Points accumulated: " + student.getPoints());
    }
      ColouredTextPrinter.printCyan("-------------------------\n");
    }

    public static int readInt(){
      while (true) {
        try {
          int userInput = -1;
          userInput = sc.nextInt();
          sc.nextLine(); // Consume newline left-over
          return userInput;
        } catch (InputMismatchException e) {
          sc.nextLine();
          ColouredTextPrinter.printRed("Invalid Input, enter an integer!");
        }
      }
    }

    public static int readInt(int min, int max , String message) {
      while (true) {
          try{
              int userInput = -1;
              userInput = sc.nextInt();
              sc.nextLine(); // Consume newline left-over
              if (userInput < min || userInput > max) {
                throw new OutOfRange();
              } else {
                return userInput;
              }
          } catch (InputMismatchException e) {
              sc.nextLine();
              ColouredTextPrinter.printRed("\nInvalid Input, Enter an integer!");
            } catch (OutOfRange e) {
              System.out.println(message);
              }
        }
    }

      //make some delay
    public static void wait(int sec){
      int milli = sec*1000;
      try {
          Thread.sleep(milli);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }

    public static void pressAnyKeyToContinue() {
      ColouredTextPrinter.printGreen("Press <Enter> key to continue...");
      try {
        System.in.read();
      } catch (Exception e) {
      }
    }

    public static void viewCamp(Camp camp){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp Date: " + camp.getCampDate());
        System.out.println("Camp Registration Closing Date: " + camp.getRegCloseDate());
        System.out.println("Camp open to faculty: " + camp.getUserGroup().toString());   
        System.out.println("Camp location: " + camp.getLocation());
        System.out.println("Camp total slots: " + camp.getTotalSlots());
        System.out.println("Camp descriptions: " + camp.getDescription());
        System.out.println("Camp staffInCharge: " + camp.getStaffInCharge());
        System.out.println("Number of days: " + camp.getNumberOfCampDays());
        System.out.println("Remaining attendee slot: " + camp.getRemainingSlot());
        System.out.println("Remaining committee slot: " + camp.getCampCommitteeRemainingSlots());
        System.out.println("\n");
    }

    public static void printRegisteredCamp(Camp camp, Student student){
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp Date: " + camp.getCampDate()); 
        System.out.println("Camp location: " + camp.getLocation());
        System.out.println("Camp descriptions: " + camp.getDescription());
        System.out.println("Camp staffInCharge: " + camp.getStaffInCharge());
        System.out.println("Number of days: " + camp.getNumberOfCampDays());
        if(camp.getCampName().equals(student.getComitteeCamp().getCampName())){
          System.out.println("Sign up as camp committee for this camp");
        }
        else{
          System.out.println("Sign up as attendee for this camp");
        }
        System.out.println("\n");
    }



    public static List<Camp> filter(){
        System.out.println("View camps by :");
        System.out.println("(1) Dates");
        System.out.println("(2) Location");
        System.out.println("(3) Faculty");
        System.out.println("Alphabetical order by default (Any number)");
        Map<String, Camp> camp = Database.getCampData();

        int choice = HelperService.readInt();

        switch (choice) {
            case 1:    //date
                List<Camp> sortedDate = sortCampsByDates(camp);
                return sortedDate;
                
            
            case 2:    //location
                List<Camp> sortedLocation = sortCampsByLocation(camp);    
                return sortedLocation;

            case 3:    //Own faculty
                List<Camp> sortedFaculty = sortCampsByFaculty(camp);
                return sortedFaculty;
              
          
            default:   //alphabetical order
            List<Camp> sortedName = sortCampsByName(camp);
                return sortedName;
                
        }
    }

    public static List<Camp> sortCampsByName (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getCampName));
        return campList;
    }

    public static List<Camp> sortCampListByName (ArrayList<String>campArr) {
        List<Camp> campList = new ArrayList<>();
        for(String campName:campArr){
          campList.add(DatabaseService.getCamp(campName));
        }
        
        Collections.sort(campList, Comparator.comparing(Camp::getCampName));
        return campList;
    }

    public static List<Camp> sortCampsByDates (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getCampDate));
        return campList;
    }
    
    public static List<Camp> sortCampsByLocation (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getLocation));
        return campList;
    }

    public static List<Camp> sortCampsByFaculty (Map<String,Camp>campMap) {
        List<Camp> campList = new ArrayList<>(campMap.values());
        Collections.sort(campList, Comparator.comparing(Camp::getUserGroup));
        return campList;
    }

    public static List<Student> sortStudentByName (Map<String,Student>campMap) {
        List<Student> studentList = new ArrayList<>(campMap.values());
        Collections.sort(studentList, Comparator.comparing(Student::getName));
        return studentList;
    }

    public static List<Student> sortStudentListByName (ArrayList<String>nameArr) {
        List<Student> studentList = new ArrayList<>();
        for(String name:nameArr){
          studentList.add(DatabaseService.getStudent(name));
        }
        
        Collections.sort(studentList, Comparator.comparing(Student::getName));
        return studentList;
    }

    public static List<Student> sortStudentByFaculty (Map<String,Student>campMap) {
        List<Student> studentList = new ArrayList<>(campMap.values());
        Collections.sort(studentList, Comparator.comparing(Student::getFaculty));
        return studentList;
    }


    public static void generateReportinTXT(){

    }
   

}

class OutOfRange extends Exception {

    /**
     * Constructor that initialises the error message
     */
    public OutOfRange() {
      super("Input is out of allowed range");
    }
  
    /**
     * Overrided constructor that initialises the error message with the specified
     * message
     * 
     * @param message error message to be displayed
     */
    public OutOfRange(String message) {
      super(message);
    }
}