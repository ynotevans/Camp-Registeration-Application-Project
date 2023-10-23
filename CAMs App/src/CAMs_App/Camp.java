package CAMs_App;

public class Camp{
    private CampInfo campInfo;
    public Camp(){
        campInfo = new CampInfo(null, 0, 0, null, 0, 0, null, null);

    }

    public CampInfo getCampInfo(){
        return campInfo;
    }

}
