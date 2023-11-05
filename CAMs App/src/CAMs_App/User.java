package CAMs_App;

import CAMs_App.enums.*;

public class User {
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

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty.toString();
    }
    
    
}