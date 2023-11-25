package CAMs_App.entity;

import CAMs_App.enums.*;

public class User {
    private String userID;      
    private String name; 
    private String password;
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

    public void setPassword(String password){
        this.password = password;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
}
