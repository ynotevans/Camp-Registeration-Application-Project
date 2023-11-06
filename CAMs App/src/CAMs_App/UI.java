package CAMs_App;
import java.util.Scanner;

import CAMs_App.entity.User;


public class UI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = new User(0,"null","null");
        System.out.println("Enter user ID: ");
        int userID = sc.nextInt();
        System.out.println("Enter password: ");
        String password = sc.next();
        System.out.println("Enter faculty: ");
        String faculty = sc.next();
        user.setUserID(userID);
        user.setPassword(password);
        user.setFaculty(faculty);
        System.out.println(user.getUserID() + " " + user.getPassword() + " " + user.getFaculty() + "\n");
    }
}
