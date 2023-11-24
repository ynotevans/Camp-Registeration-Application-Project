package CAMs_App.entity;

import CAMs_App.enums.*;

public class User {
    private String userID;      
    private String name; 
    private String password;
    private Identity identity;
    private Faculty faculty;

    

    public User(String userID, String password, Faculty faculty, String name){
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getName() {
        return name;
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
