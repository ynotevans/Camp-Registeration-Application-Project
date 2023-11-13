package CAMs_App.entity;

import CAMs_App.enums.*;

public class User {
    private String userID;       
    private String password;
    private Identity identity;
    private Faculty faculty;

    

    public User(String userID, String password, Faculty faculty){
        this.userID = userID;
        this.password = "password";  //should use default password right???
        this.faculty = faculty;
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

    public boolean setIdentity(Identity status) {
        this.identity = status;
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

    public Identity getIdentity() {
        return identity;
    }
}
