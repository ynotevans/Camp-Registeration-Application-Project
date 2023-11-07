package CAMs_App.entity;

import CAMs_App.enums.*;
import java.util.Scanner;

public class User {
     Scanner scan = new Scanner(System.in);

    private int userID;       //UserID should be String?
    private String password;
    private Status status;
    private Faculty faculty;

    

    public User(int userID, String password, Faculty faculty, Status status){
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.status = status;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty.toString();
    }

    public Status getStatus() {
        return status;
    }

    public void changePassword(){
        System.out.println("Changing password...");
        System.out.println("Enter old password: ");
        String oldPwd = scan.next();
        if(oldPwd == this.getPassword()){
             System.out.println("New password: ");
             String newPwd1 = scan.next();

             System.out.println("Confirm new password");
             String newPwd2 = scan.next();
             
             while(newPwd1 != newPwd2){
                System.out.println("Password deosn't match. Reenter your new password");
                System.out.println("New Password"); 
                newPwd1 = scan.next();
                System.out.println("Confirm Password: ");
                newPwd2 = scan.next();
                }
            
            setPassword(newPwd2);
            System.out.println("Password updated..");
        }
        else{
            System.out.println("Wrong password!!Attempt failed");
        }
    }      
}
