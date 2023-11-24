package CAMs_App.service;

import java.util.InputMismatchException;

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

 
   public static int readInt(){
    while (true) {
      try {
        int userInput = -1;
        userInput = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        return userInput;
      } catch (InputMismatchException e) {
        sc.nextLine();
        System.out.println("Invalid Input, enter an integer!");
      }
    }
  }

 
  public static int readInt(int min, int max) {
    while (true) {
      try {
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
        System.out.println("\nInvalid Input, Enter an integer!");
      } catch (OutOfRange e) {
        System.out.println("\nInput is out of allowed range");
      }
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
        System.out.println("Camp remaining slot: " + camp.getRemainingSlot());
        System.out.println("\n");
    }

    public static void viewEnquiries(Enquiries q){
      System.out.println("Inquirer:" + q.getInquirer());
      System.out.println("Enquiry: " + q.getEnquiry());

      System.out.println("Answerer: " + q.getAnswerer());
      System.out.println("Answer: " + q.getAnswer());
  }

    public static void printSuggestions(Suggestions s){
      System.out.println("Suggested by: " + s.getSuggestBy());
      System.out.println("Suggested by: " + s.getSuggestion());
      
      System.out.print("Status: ");     
      if(s.getProcessed()){
        if(s.getAccepted()){
          System.out.println("Suggestion accepted!!");
        }
        else{
          System.out.println("Suggestion rejected");
        }
      }
      else{
        System.out.println("Suggestion under process...");
      }
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
