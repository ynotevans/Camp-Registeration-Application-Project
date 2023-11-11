package CAMs_App.entity;

import CAMs_App.enums.*;
public class Staff extends User{
    public Staff(String userID, String password,Faculty faculty) {
        super(userID, password, faculty);
        super.setStatus(Status.staff);
    }
    @Override
    public void setFaculty(Faculty faculty) {
        // TODO Auto-generated method stub
        super.setFaculty(faculty);
    }

    @Override
    public void setUserID(String userID) {
        // TODO Auto-generated method stub
        super.setUserID(userID);
    }

    @Override
    public String getFaculty() {
        // TODO Auto-generated method stub
        return super.getFaculty();
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return super.getPassword();
    }

    @Override
    public Status getStatus() {
        // TODO Auto-generated method stub
        return super.getStatus();
    }

    @Override
    public String getUserID() {
        // TODO Auto-generated method stub
        return super.getUserID();
    }
}
