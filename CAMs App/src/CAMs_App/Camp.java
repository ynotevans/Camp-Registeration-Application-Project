package CAMs_App;

import java.util.ArrayList;

public class Camp{

    private ArrayList<Student> attendees = new ArrayList<String>();
    private ArrayList<Student> committee = new ArrayList<String>();
    private ArrayList<Enquiries> enquiries = new ArrayList<String>();
    private ArrayList<Suggestion> suggestions = new ArrayList<String>();
    private CampInfo campInfo;
    
    public Camp(){
        campInfo = new CampInfo(null, 0, 0, null, 0, null, null, null);
    }

    public CampInfo getCampInfo(){
        return campInfo;
    }

}
