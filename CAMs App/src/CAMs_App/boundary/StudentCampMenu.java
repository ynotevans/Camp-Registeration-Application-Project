package CAMs_App.boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import CAMs_App.controllers.StudentController;
import CAMs_App.data.AuthData;
import CAMs_App.service.HelperService;
import CAMs_App.service.ColouredTextPrinter;
public class StudentCampMenu implements Menu{
    StudentController studentController = new StudentController();
    StudentMenu studentMenu = new StudentMenu();

    public void printMenu(){
        HelperService.clearScreen();
        HelperService.printRoute("Student Camp Menu ---> "+ AuthData.getCurrentCamp().getCampName());
        ColouredTextPrinter.printBlue("Available actions for this camp: ");
        ColouredTextPrinter.printBlue("(1) Register camp");
        ColouredTextPrinter.printBlue("(2) Withdraw camp");
        ColouredTextPrinter.printBlue("(3) Submit enquiries for this camp");
        ColouredTextPrinter.printBlue("(4) View your submitted enquiries");
        ColouredTextPrinter.printBlue("(5) Delete Enquiries ");
        ColouredTextPrinter.printBlue("(6) Go to previous page");
        ColouredTextPrinter.printBlue("(7) Logout\n");
    }

    

    public void viewApp(){
        Scanner sc = new Scanner(System.in);
        int choice;
            do{
                this.printMenu();
                System.out.print("Enter ur selection: ");
                choice = HelperService.readInt();

                switch (choice) {
                    case 1:    //register camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Student Camp Menu ---> Register Camp");
                        System.out.println("You want to register as: ");
                        System.out.println("(1) Attendees");
                        System.out.println("(2) Camp Committee\n");
                        System.out.print("Enter your selection: ");
                        try {
                        int choice1 = HelperService.readInt();
                            if(choice1 == 1){
                                studentController.joinAsAttendee();    //update campName

                            }
                            else if (choice1 == 2){
                                if(studentController.joinAsCommittee()==true){
                                    HelperService.wait(2);
                                    studentMenu.viewApp();
                                }
                                else {
                                    HelperService.wait(2);
                                    studentMenu.viewApp();
                                }
                            }

                            else {
                                System.out.println("Invalid input, returning to menu...");
                            }
                            
                        } catch (InputMismatchException error){
                            System.out.println("Invalid input, returning to menu...");
                        }

                        HelperService.wait(2);
                        viewApp();
                        break;

                    case 2:    //withdraw camp
                        HelperService.clearScreen();
                        HelperService.printRoute("Student Camp Menu ---> Withdraw Camp");
                        studentController.withdrawCamp();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;
                    
                    case 3:    //submit enquiry
                        HelperService.clearScreen();
                        HelperService.printRoute("Student Camp Menu ---> Create Enquiry");
                        studentController.createEnquiry();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;
                    
                    case 4:    //view submitted enquiries
                        HelperService.clearScreen();
                        HelperService.printRoute("Student Camp Menu ---> View Your Enquiries");
                        studentController.viewEnquiry();
                        HelperService.pressAnyKeyToContinue();
                        this.viewApp();
                        break;

                    case 5: 
                        HelperService.clearScreen();
                        HelperService.printRoute("Student Camp Menu ---> Delete Your Enquiries");
                        studentController.deleteEnquiries();
                        HelperService.pressAnyKeyToContinue();
                        
                    case 6: //go to previous page
                        studentMenu.viewApp();
                        break;


                    case 7:    //logout
                        System.out.println("Logging out...");
                        HelperService.wait(3);
                        studentController.logout();
                        HelperService.wait(1);
                        MainMenu menu = new MainMenu();
                        menu.viewApp();
                        break;
                    
                    default:
                        System.out.println("Invalid selection, please select again...");
                        break;
                }

            }while(choice != 5);
        
            sc.close();
        }
    }

