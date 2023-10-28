package CAMs_App;

public class User {
    private int userID;
    private String password;
    enum Faculty{
        NIE, NBS, EEE, SOH, MAE, SCSE,
        SPMS, SSS, CCEB, LKC, SBS, CEE,
        MSE, WKWSOC, ADM, ASE, SCBE;
    };

    public User(int userID, String password, String faculty){
        this.userID = userID;
        this.password = password;
        this.Faculty = faculty;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }
    
    
}