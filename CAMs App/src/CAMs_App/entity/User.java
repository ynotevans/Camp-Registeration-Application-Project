package CAMs_App.entity;

import CAMs_App.enums.*;

public class User {
    private String userID;       
    private String password;
    private Status status;
    private Faculty faculty;

    

    public User(String userID, String password, Faculty faculty){
        this.userID = userID;
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean setPassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(this.password))
            return false;
           
        this.password = newPassword;
        return true;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public boolean setStatus(Status status) {
        this.status = status;
        return true;
    }

    public String getUserID() {
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
}
